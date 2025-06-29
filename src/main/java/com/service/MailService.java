package com.service;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender mailSender;
	
	public void sendWelcomeEmailold(String email)
	{
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("tejasshah2k19@gmail.com");
		mailMessage.setTo(email);
		mailMessage.setSubject("Welcome email"); 
		mailMessage.setText("Welcome to alibaba , we are happy to server you");
		
		mailSender.send(mailMessage);
	}
	public void sendWelcomeEmail(String email,String firstName)
	{
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper;
		try {
			ClassPathResource resource = new ClassPathResource("templates/WelcomeMail.html");
			
			String htmlData = resource.getContentAsString(Charset.defaultCharset()); 
			htmlData = htmlData.replace("{{name}}", firstName);
			
			helper = new MimeMessageHelper(message,true,"UTF-8");
			helper.setFrom("prathambodan2007@gmail.com");
			helper.setTo(email);
			helper.setSubject("Welcome Mail");
			helper.setText(email);
			helper.setText(htmlData, true);
			mailSender.send(message);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
