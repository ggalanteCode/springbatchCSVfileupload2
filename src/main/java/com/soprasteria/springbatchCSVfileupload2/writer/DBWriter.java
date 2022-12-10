package com.soprasteria.springbatchCSVfileupload2.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.soprasteria.springbatchCSVfileupload2.model.User;
import com.soprasteria.springbatchCSVfileupload2.repo.UserRepo;

public class DBWriter implements ItemWriter<User> {
	
	private static final Logger logger = LoggerFactory.getLogger(DBWriter.class);

	@Autowired
	private UserRepo userRepo;

	@Override
	public void write(List<? extends User> items) throws Exception {
		// TODO Auto-generated method stub
		items.forEach(user -> logger.info(user + " inserted in the database!"));
		userRepo.saveAll(items);
	}

}
