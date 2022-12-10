package com.soprasteria.springbatchCSVfileupload2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soprasteria.springbatchCSVfileupload2.utils.EmailUtils;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info("!!! JOB FINISHED! Time to verify the results");
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			logger.error("!!! JOB FAILED...");
			emailUtils.send("Job Error: FAILED", "CSV FILE UPLOAD: The following exceptions occurred: "
					+ jobExecution.getAllFailureExceptions().toString());
		}
	}

}
