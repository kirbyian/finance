package com.kirby.finance;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.kirby.finance.controller.ExchangeController;

import lombok.extern.slf4j.Slf4j;

@ComponentScan(basePackages = { "com.kirby.finance.*" })
@EntityScan(basePackages = { "com.kirby.finance.model" })
@EnableJpaRepositories("com.kirby.finance.repository")

@Slf4j
@EnableScheduling
@EnableAspectJAutoProxy
@PropertySource("classpath:config.properties")
@SpringBootApplication
public class FinanceApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(env.getProperty("finance.email.source.email"));
		mailSender.setPassword(env.getProperty("finance.email.source.password"));
		
		log.info("####### EMAIL PARAMS ########");
		log.info("USERNAME:"+env.getProperty("finance.email.source.email"));
		log.info("PASSWORD:"+env.getProperty("finance.email.source.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}
