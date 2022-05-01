package com.kirby.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public EmailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	/**
	 * Simple method for sending emails.
	 * 
	 * @param subject
	 * @param targetEmailAddress
	 * @param emailText
	 * @param sourceEmailAddress
	 */
	public void sendEmailMessage(String subject, String targetEmailAddress, String emailText,
			String sourceEmailAddress) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sourceEmailAddress);
		message.setTo(targetEmailAddress);
		message.setSubject(subject);
		message.setText(emailText);
		emailSender.send(message);
	}

}
