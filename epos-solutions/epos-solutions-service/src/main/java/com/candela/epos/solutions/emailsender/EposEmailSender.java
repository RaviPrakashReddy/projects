package com.candela.epos.solutions.emailsender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class EposEmailSender {

	private Logger log = LoggerFactory.getLogger(EposEmailSender.class);

	@Autowired
	private JavaMailSender sender;

	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo("ravi.reddy@candelalabs.io");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Epos Solutions");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		log.info("=========== JavaMailSender before calling send() method: ===========");
		sender.send(message);
		return "Mail Sent Success!";
	}
}
