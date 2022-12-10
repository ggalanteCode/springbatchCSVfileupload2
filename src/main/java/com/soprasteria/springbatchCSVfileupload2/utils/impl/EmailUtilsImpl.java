package com.soprasteria.springbatchCSVfileupload2.utils.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.soprasteria.springbatchCSVfileupload2.utils.EmailUtils;

@Component
public class EmailUtilsImpl implements EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.properties.mail.smtp.from}")
	private String from;
	
	@Value("${spring.mail.properties.mail.smtp.to}")
	private String to;

	@Override
	public void send(String subject, String body) {
		// TODO Auto-generated method stub
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);// true indicates multipart message
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body, true); // true indicates body is html
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
