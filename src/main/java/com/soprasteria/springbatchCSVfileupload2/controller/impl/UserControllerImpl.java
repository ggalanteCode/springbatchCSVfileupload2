package com.soprasteria.springbatchCSVfileupload2.controller.impl;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.soprasteria.springbatchCSVfileupload2.controller.UserController;
import com.soprasteria.springbatchCSVfileupload2.service.UserService;

@RestController
@RequestMapping("/users2")
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserService userService;
	
	@Override
	@PostMapping("/uploadFile")
	public Boolean uploadFile(@RequestParam("file") MultipartFile file) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// TODO Auto-generated method stub
		return userService.importUser(file);
	}

}
