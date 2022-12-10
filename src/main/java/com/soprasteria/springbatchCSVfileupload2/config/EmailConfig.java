package com.soprasteria.springbatchCSVfileupload2.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.soprasteria.springbatchCSVfileupload2.utils.EmailUtils;
import com.soprasteria.springbatchCSVfileupload2.utils.impl.EmailUtilsImpl;

@Configuration
public class EmailConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailConfig.class);
	
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${security.require-ssl}")
    private boolean requireSsl;
    @Value("${spring.mail.username}")
    private String username;
	
	@Bean
    public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("spring.mail.properties.mail.smtp.auth", auth);
        mailProperties.put("spring.mail.properties.mail.smtp.starttls.enable", starttls);
        mailProperties.put("security.require-ssl", requireSsl);
        
        logger.info("HOST: " + host);
        logger.info("PORT: " + port);
        logger.info("AUTH: " + auth);
        logger.info("STARTTLS: " + starttls);
        logger.info("REQUIRE SSL: " + requireSsl);
        logger.info("USERNAME: " + username);
        logger.info("PASSWORD: " + password);
        
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }
	
	@Bean
	public EmailUtils emailUtils() {
		return new EmailUtilsImpl();
	}

}
