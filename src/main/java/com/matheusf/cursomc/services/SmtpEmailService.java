package com.matheusf.cursomc.services;

<<<<<<< HEAD
import javax.mail.internet.MimeMessage;

=======
>>>>>>> 4a9cdea96ecb01a72048b0ed288448f27b06ced1
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
<<<<<<< HEAD
import org.springframework.mail.javamail.JavaMailSender;
=======
>>>>>>> 4a9cdea96ecb01a72048b0ed288448f27b06ced1

public class SmtpEmailService extends AbstractEmailService {
		
	@Autowired
	private MailSender mailSender;
	
<<<<<<< HEAD
	@Autowired
	private JavaMailSender javaMailSender;
	
=======
>>>>>>> 4a9cdea96ecb01a72048b0ed288448f27b06ced1
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email...");
		mailSender.send(msg);
		LOG.info("Email enviado!");		
	}
<<<<<<< HEAD

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando email...");
		javaMailSender.send(msg);
		LOG.info("Email enviado!");			
	}
=======
>>>>>>> 4a9cdea96ecb01a72048b0ed288448f27b06ced1
}
