package io.inouty.jswdb.batch.jobs;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest()
@SpringBatchTest()
public class JswdbMoviesJobIntegrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("startDate", formatter.parse("2018-01-31"))
                .addDate("endDate", formatter.parse("2018-01-30"))
                .addLong("pageSize", 3L)
                .toJobParameters();
        final JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        final ExecutionContext context = jobExecution.getExecutionContext();
        final Integer recordsCount = context.getInt("recordsCount");
        assertThat("recordsCount should be equal to recordsWritten", recordsCount, greaterThan(0));
        assertThat("Exit status should be COMPLETED", jobExecution.getExitStatus(), is(ExitStatus.COMPLETED));
    }

}
