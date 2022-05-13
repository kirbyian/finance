package com.kirby.finance.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

	@Autowired
	private EmailService emailService;

	@Test
	void testSendEmailMessage() throws Exception {
		emailService.sendEmailMessage("Test", "iankirby1991@gmail.com", "Sample Email", "iankirby1991@hotmail.com");
	}

}
