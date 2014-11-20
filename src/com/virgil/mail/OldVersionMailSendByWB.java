package com.virgil.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OldVersionMailSendByWB {
public static void main(String[] args){
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "192.168.102.82");
		props.put("mail.smtp.port","25");
		props.put("mail.transport.protocol","smtp");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("liuwj@ctrip.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("liuwj@ctrip.com"));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			message.setSubject("°æ≤˙∆∑&≤‚ ‘&UED»±œ›“≈¡Ù¡–±Ì°ø__"+simpleDateFormat.format(new Date()));
			message.setContent("≤‚ ‘","text/html;charset=utf-8");
			

			Transport transport=session.getTransport("smtp");
			transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
