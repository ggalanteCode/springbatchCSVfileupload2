package com.soprasteria.springbatchCSVfileupload2.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soprasteria.springbatchCSVfileupload2.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Override
	public Boolean importUser(MultipartFile usersFile) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		// TODO Auto-generated method stub
		File fileToImport = fromMultipartFileToFile(usersFile);
		JobParameters jobParameters = new JobParametersBuilder()
		.addLong("startAt", System.currentTimeMillis() + 10000)
        .addString("fullPathFileName", fileToImport.getAbsolutePath())
        .toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		while(jobExecution.isRunning()) {
			
		}
		return jobExecution.getStatus().equals(BatchStatus.COMPLETED);
	}
	
	private File fromMultipartFileToFile(MultipartFile mpFile) {
		File file = new File("src//main//resources//" + mpFile.getOriginalFilename());
		try (OutputStream os = new FileOutputStream(file)) {
		    os.write(mpFile.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

}
