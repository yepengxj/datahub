package com.asiainfo.bdx.datahub.common.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.asiainfo.bdx.datahub.common.DHConstants;

public class MailUtil {

	private static JavaMailSenderImpl sender = new JavaMailSenderImpl();
	static{
		sender.setHost(DHConstants.MAIL_HOST);
		sender.setUsername(DHConstants.MAIL_USERNAME);
		sender.setPassword(DHConstants.MAIL_PASSWORD);
 
	}
	
	public static void sendMail(String address,String subject,String text){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject(subject);
		mailMessage.setText(text);
		mailMessage.setTo(address);  
	    mailMessage.setFrom(DHConstants.MIAL_FROM);
	    
 	    sender.send(mailMessage);
	    
	}
	public static void main(String[] args){  
		//sendEmail
		MailUtil.sendMail("wangyf15@asiainfo.com", "DataHub平台密码重置", "重置后密码：666");
	}
}
