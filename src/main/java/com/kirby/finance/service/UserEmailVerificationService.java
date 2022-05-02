package com.kirby.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserEmailVerificationService implements EmailServiceConstants {

	private EmailService emailService;

	@Autowired
	private Environment env;

	public UserEmailVerificationService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * Sends a Verification email to the user who attempting to be registered. A
	 * token is generated and included in the URL as part of the email text.
	 * 
	 * @param toEmailAddress
	 * @param url
	 * @param token
	 */
	public void sendVerificationEmail(String toEmailAddress, String url, String token) {

		StringBuilder emailTextBuilder = new StringBuilder();
		emailTextBuilder.append(verificationBody);
		emailTextBuilder.append(url);
		emailTextBuilder.append("/user/verify");
		emailTextBuilder.append("?token=");
		emailTextBuilder.append(token);
		log.info("Sending Verification Email, SourceEmail address:" + env.getProperty("finance.email.source.email"));
		log.info("Email Text:" + emailTextBuilder.toString());

		String sourceEmail = env.getProperty("finance.email.source.email");

		try {
			emailService.sendEmailMessage(verificationSubject, toEmailAddress, emailTextBuilder.toString(),
					sourceEmail);
		} catch (Exception e) {
			log.error("Unable to send Verification email:" + emailTextBuilder.toString());
			log.error("FROM SOURCE Email:" + sourceEmail);
			log.error(e.getStackTrace().toString());

		}
	}

}
