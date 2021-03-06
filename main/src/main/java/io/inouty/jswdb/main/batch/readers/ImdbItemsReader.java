package io.inouty.jswdb.main.batch.readers;

import io.inouty.jswdb.core.domain.SearchPagination;
import io.inouty.jswdb.core.ports.input.ScrapeMovieSearchPort;
import io.inouty.jswdb.core.ports.output.MoviesSearchScraperPort;
import io.inouty.jswdb.core.ports.usecases.ScrapeMovieSearchUseCase;
import io.inouty.jswdb.scraping.adapters.MoviesSearchScraperAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

@Component()
public class ImdbItemsReader implements ItemReader<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbItemsReader.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String searchUrl;

    private ItemReader<String> moviesIdsDelegate = null;

    @Value("${imdb.urls.search}")
    private String searchUrlTemplate;

    @Value("#{T(java.util.regex.Pattern).compile('${imdb.patterns.pagination}')}")
    private Pattern paginationPattern;

    @Value("${imdb.search.page-size:50}")
    private Long searchPageSize;

    private ExecutionContext context;

    @BeforeStep()
    public void beforeStep(StepExecution stepExecution) {
        this.context = stepExecution.getJobExecution().getExecutionContext();
        this.context.put("startTime", System.currentTimeMillis());
        final JobParameters parameters = stepExecution.getJobParameters();
        final Date startDate = parameters.getDate("startDate");
        final Date endDate = parameters.getDate("endDate");
        if (startDate == null || endDate == null) {
            final String errorMsg = "Both start and end date are required to execute the job";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(errorMsg);
            }
            throw new IllegalArgumentException(errorMsg);
        }
        final Long pageSize = parameters.getLong("pageSize");
        if (pageSize != null) {
            if (LOGGER.isInfoEnabled() && pageSize != searchPageSize) {
                LOGGER.info("The fetch page size will be changed from its default value({}) to a new one {}", searchPageSize, pageSize);
            }
            searchPageSize = pageSize;
        }
        this.searchUrl = String.format(searchUrlTemplate, dateFormat.format(startDate), dateFormat.format(endDate), searchPageSize);
    }

    private void scrape() {
        final MoviesSearchScraperPort scraper = new MoviesSearchScraperAdapter(searchUrl, paginationPattern);
        final ScrapeMovieSearchPort scrapeUseCase = new ScrapeMovieSearchUseCase(scraper);
        final Set<String> moviesIds = scraper.extractMoviesIds();
        this.moviesIdsDelegate = new IteratorItemReader(moviesIds);
        final SearchPagination pagination = scrapeUseCase.extractPaginationInfo();
        final Optional<String> nextPage = pagination.getNextPageUrl();
        this.searchUrl = nextPage.isPresent() ? nextPage.get() : null;
        this.context.put("recordsCount", pagination.getRecordsCount());
    }

    @Override
    public synchronized String read() throws Exception {
        if (searchUrl == null) {
            return null;
        }
        if (moviesIdsDelegate == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("A new page is going to be scraped");
            }
            this.scrape();
        }
        String movieId = this.moviesIdsDelegate.read();
        if (movieId == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("A new page is going to be scraped");
            }
            this.scrape();
            movieId = this.moviesIdsDelegate.read();
        }
        if (movieId == null && LOGGER.isInfoEnabled()) {
            LOGGER.info("No more pages to be scraped");
        }
        return movieId;
    }

}
