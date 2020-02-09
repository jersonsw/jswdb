package io.inouty.jswdb.main.web.http.controllers;

import io.inouty.jswdb.main.dtos.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobsHttpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobsHttpController.class);
    private final JobLauncher jobLauncher;
    private final Job moviesJob;


    public JobsHttpController(@Qualifier("moviesJob") Job moviesJob, JobLauncher jobLauncher) {
        this.moviesJob = moviesJob;
        this.jobLauncher = jobLauncher;
    }


    @PostMapping("/run")
    public ResponseEntity<?> execute(@RequestBody Request request) {
        try {
            final JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("startDate", request.getStartDate())
                    .addDate("endDate", request.getEndDate())
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();
            final JobExecution jobExecution = this.jobLauncher.run(this.moviesJob, jobParameters);
            return ResponseEntity.ok(jobExecution.getStatus().getBatchStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            LOGGER.error("Job execution error", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error trying to launch the job.");
    }
}
