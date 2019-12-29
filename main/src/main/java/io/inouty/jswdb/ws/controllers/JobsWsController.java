package io.inouty.jswdb.ws.controllers;

import io.inouty.jswdb.dtos.Request;
import io.inouty.jswdb.dtos.Event;
import io.inouty.jswdb.enums.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Iterator;
import java.util.Set;

@Controller
@MessageMapping("/jobs")
public class JobsWsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobsWsController.class);
    private final JobLauncher jobLauncher;
    private final Job jswDbProcessorJob;
    private final JobOperator jobOperator;
    private final SimpMessageSendingOperations messagingTemplate;

    public JobsWsController(
            @Qualifier("asyncJobLauncher") JobLauncher jobLauncher,
            @Qualifier("jswDbProcessorJob") Job jswDbProcessorJob,
            SimpMessageSendingOperations messagingTemplate,
            JobOperator jobOperator
    ) {
        this.jobLauncher = jobLauncher;
        this.jswDbProcessorJob = jswDbProcessorJob;
        this.jobOperator = jobOperator;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/run")
    public void runJob(@Payload Request request) {
        String msg = "";
        final JobParameters jobParameters = new JobParametersBuilder()
                .addDate("startDate", request.getStartDate())
                .addDate("endDate", request.getEndDate())
                .toJobParameters();
        EventStatus eventStatus = EventStatus.JOB_STOPPED;
        try {
            final JobExecution jobExecution = this.jobLauncher.run(this.jswDbProcessorJob, jobParameters);
            eventStatus = EventStatus.JOB_RUNNING;
            LOGGER.info("The job that runs the crawler has been launched. Current status: {}", jobExecution.getStatus().name());
            return;
        } catch (JobExecutionAlreadyRunningException e) {
            msg = "A job instance with the same date range was already started.";
            LOGGER.error(msg, e);
        } catch (JobRestartException e) {
            msg = "A job instance already ran with the same date range.";
            LOGGER.error(msg, e);
        } catch (JobInstanceAlreadyCompleteException e) {
            msg = "A job instance already ran with the same date range.";
            LOGGER.error(msg, e);
        } catch (JobParametersInvalidException e) {
            msg = "Invalid date range parameters";
            LOGGER.error(msg, e);
        }
        this.messagingTemplate.convertAndSend("/topic/events", new Event(eventStatus, msg));
    }

    @MessageMapping("/stop")
    public void stopJob() {
        String msg = "All jobs executions have been stopped";
        Set<Long> executions = null;
        try {
            executions = jobOperator.getRunningExecutions("jswDbProcessorJob");
        } catch (NoSuchJobException e) {
            msg = "No jobs executions found";
            LOGGER.error(msg);
        }
        if (executions != null) {
            final Iterator<Long> iterator = executions.iterator();
            while (iterator.hasNext()) {
                try {
                    jobOperator.stop(iterator.next());
                } catch (NoSuchJobExecutionException e) {
                    LOGGER.error(e.getMessage(), e);
                } catch (JobExecutionNotRunningException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        this.messagingTemplate.convertAndSend("/topic/events", new Event(EventStatus.JOB_STOPPED, msg));
    }


}
