package io.inouty.jswdb.main.batch.readers;

import io.inouty.jswdb.domain.SearchPagination;
import io.inouty.jswdb.scraping.MoviesSearchScraperImpl;
import io.inouty.jswdb.usecases.ScrapeMoviesIdsUseCase;
import io.inouty.jswdb.usecases.ScrapeMoviesPaginationUseCase;
import io.inouty.jswdb.usecases.ports.scrapers.MoviesSearchScraper;
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
        Long pageSize = parameters.getLong("pageSize");
        if (pageSize != null) {
            if (LOGGER.isInfoEnabled() && pageSize != searchPageSize) {
                LOGGER.info("The fetch page size will be changed from its default value({}) to a new one {}", searchPageSize, pageSize);
            }
            searchPageSize = pageSize;
        }
        this.searchUrl = String.format(searchUrlTemplate, dateFormat.format(startDate), dateFormat.format(endDate), searchPageSize);
    }

    private void scrape() {
        final MoviesSearchScraper scraper = new MoviesSearchScraperImpl(searchUrl, paginationPattern);
        final ScrapeMoviesIdsUseCase scrapeMoviesIdsUseCase = new ScrapeMoviesIdsUseCase(scraper);
        final Set<String> moviesIds = scrapeMoviesIdsUseCase.execute();
        this.moviesIdsDelegate = new IteratorItemReader(moviesIds);
        final ScrapeMoviesPaginationUseCase pagScraper = new ScrapeMoviesPaginationUseCase(scraper);
        final SearchPagination pagination = pagScraper.execute();
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
