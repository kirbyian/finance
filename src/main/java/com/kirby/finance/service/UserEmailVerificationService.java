package com.kirby.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserEmailVerificationService implements EmailServiceConstants {

	private EmailService emailService;

	@Autowired
	private Environment env;

	public UserEmailVerificationService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void sendVerificationEmail(String toEmailAddress, String url, String token) {

		StringBuilder emailTextBuilder = new StringBuilder();
		emailTextBuilder.append(verificationBody);
		emailTextBuilder.append(url);
		emailTextBuilder.append("/user/verify");
		emailTextBuilder.append("?token=");
		emailTextBuilder.append(token);

		emailService.sendEmailMessage(verificationSubject, toEmailAddress, emailTextBuilder.toString(),
				env.getProperty("finance.email.source.email"));
	}

}
