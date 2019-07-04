package com.psoft.UCDb.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Bean;
 
public class EmailSender{
	  private String mail;
      private String pass;
      private String userEmail;
      
	  public EmailSender(String userEmail) {
		  this.mail = "UCDBTeam@gmail.com";
		  this.pass = "#projsw20191";
		  this.userEmail = userEmail;
	  }
	  
	  public void sendMail() {
	    Properties props = new Properties();
	    
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	 
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication() {
	                 return new PasswordAuthentication(mail, pass);
	           }
	      });
	 
	    session.setDebug(true);
	    try {
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(mail)); 
	 
	      Address[] toUser = InternetAddress.parse(this.userEmail);  
	 
	      message.setRecipients(Message.RecipientType.TO, toUser);
	      message.setSubject("Bem vindo ao UCDB");
	      message.setText("Seja bem vindo ao UCDB, sua conta já está devidamente cadastrada");
	      Transport.send(message);
	 
	      System.out.println("Feito!!!");
	 
	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	  }
}