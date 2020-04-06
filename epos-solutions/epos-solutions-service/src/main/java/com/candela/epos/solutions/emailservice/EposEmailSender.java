package com.candela.epos.solutions.emailservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import com.candela.epos.solutions.db.repository.UserRepository;
import com.candela.epos.solutions.model.User;

@Configuration
public class EposEmailSender {

	private Logger log = LoggerFactory.getLogger(EposEmailSender.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	public void sendMail(String email) {
		log.info("Printing Email in EmailSenderService class:: " + email);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		try {
			User existingUser = userRepository.findByEmail(email);
			if (existingUser != null) {
				// mailMessage.setTo(existingUser.getEmail());
				mailMessage.setTo("epos.solutions123@gmail.com");
				mailMessage.setSubject("Mail from E-POS Solutions: please reset password");
				mailMessage.setFrom("epos.solutions123@gmail.com");
				mailMessage.setText("Here is your Temporarypassword: " + existingUser.getPassword());
				mailMessage.setText("To complete the password reset process, please click here: ");

				// Send the email
				emailSenderService.sendEmail(mailMessage);
			} else {
				log.info("This email address does not exist please try again!");
			}

		} catch (MailException mailException) {
			log.error("Exception while sending email to user:: ", mailException);
		}
	}

}
