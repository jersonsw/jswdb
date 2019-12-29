package io.inouty.jswdb.batch.listeners;

import io.inouty.jswdb.dtos.Event;
import io.inouty.jswdb.enums.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class ImdbJobListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbJobListener.class);
    private SimpMessageSendingOperations messagingTemplate;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public ImdbJobListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        JobParameters jobParameters = jobExecution.getJobParameters();
        String paramsStr = "";
        if (jobParameters != null) {
            Map<String, JobParameter> parameters = jobParameters.getParameters();
            if (parameters.size() > 0) {
                for (Map.Entry<String, JobParameter> entry : parameters.entrySet()) {
                    JobParameter jobParameter = entry.getValue();
                    String value = "";
                    if (jobParameter.getType().equals(JobParameter.ParameterType.DATE)){
                        value = formatter.format((Date) jobParameter.getValue());
                    }else {
                        value = jobParameter.getValue().toString();
                    }
                    paramsStr += String.format(", %s: %s", entry.getKey(), value);
                }
            }
        }
        messagingTemplate.convertAndSend("/topic/events", new Event(EventStatus.JOB_RUNNING, "Job started with the following parameters: " + paramsStr.substring(1)));
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>> JOB STARTED");
        super.beforeJob(jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String status = jobExecution.getStatus().getBatchStatus().name();
        messagingTemplate.convertAndSend("/topic/events", new Event(EventStatus.JOB_STOPPED, "Job finished with the status: " + status));
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<< JOB FINISHED with status: {}", status);
    }


}