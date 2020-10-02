package com.matheusf.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.matheusf.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
