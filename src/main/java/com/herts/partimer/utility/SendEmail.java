package com.herts.partimer.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void SendEmailtoStudent(String to, String verificationCode) {
		String subject = "Email verification code";
		String msg = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Verify your Email</title></head><body style=\"font-family: Helvetica, Arial, sans-serif; margin: 0px; padding: 0px; background-color: #ffffff;\"><table role=\"presentation\"\r\n"
				+ "    style=\"width: 100%; border-collapse: collapse; border: 0px; border-spacing: 0px; font-family: Arial, Helvetica, sans-serif; background-color: rgb(239, 239, 239);\"><tbody><tr><td align=\"center\" style=\"padding: 1rem 2rem; vertical-align: top; width: 100%;\"><table role=\"presentation\" style=\"max-width: 600px; border-collapse: collapse; border: 0px; border-spacing: 0px; text-align: left;\"><tbody><tr><td style=\"padding: 40px 0px 0px;\"><div style=\"text-align: left;\"><div style=\"padding-bottom: 20px;\"><h1 style=\"color: cornflowerblue;\">PARTIMER</h1></div></div><div style=\"padding: 20px; background-color: rgb(255, 255, 255);\"><div style=\"color: rgb(0, 0, 0); text-align: left;\"><h1 style=\"margin: 1rem 0\">Verification code</h1><p style=\"padding-bottom: 16px\">Please use the verification code below to sign in.</p><div style=\"text-align: center;\"><p style=\"padding-bottom: 16px; padding-top: 16px; background-color: cornflowerblue;\"><strong style=\"font-size: 130%\">{CODE}</strong></p></div><p style=\"padding-bottom: 30px\">If you didn't request this, you can ignore this email.</p><p style=\"padding-bottom: 16px\">Thanks,<br>The PARTIMER team</p></div></div><div style=\"padding-top: 20px; color: rgb(153, 153, 153); text-align: center;\"><p style=\"padding-bottom: 16px\">Made with <strong>LOVE</strong> in UK</p></div></td></tr></tbody></table></td></tr></tbody></table></body></html>";
		msg = msg.replace("{CODE}", verificationCode);
		final String from = "the.partimer.team@gmail.com";
		final String password = "szwpimcygkvdjtbx";
		send(from, password, to, subject, msg);
	}

	private static void send(String pFrom, String pPassword, String pTo, String pSubject, String pMessage) {
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(pFrom, pPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(pFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pTo));
			message.setSubject(pSubject);
			message.setContent(pMessage, "text/html");
			Transport.send(message);
			System.out.println("send mail Successfully to " + pTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
