package org.inneo.services.servicos;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
	private final JavaMailSender mailSender;
	
	public void sending(String email, String assunto, String conteudo) {
		SimpleMailMessage message = new SimpleMailMessage();	    
		message.setFrom("inneobr@gmail.com");
        message.setTo(email);
        message.setSubject(assunto);
        message.setText(conteudo);
        mailSender.send(message);	   			
	}
}
