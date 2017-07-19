package com.phelps.ps.com.autotest.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class EmailService {
	public final static Logger logger = Logger.getLogger(EmailService.class);
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	public static void generateAndSendEmail(List<String> toList, String subject, String message) throws AddressException,
			MessagingException {
		generateAndSendEmail(toList, null, subject, message);
	}

	public static void generateAndSendEmail(List<String> toList, List<String> ccList, String subject, String message)
			throws AddressException, MessagingException {

		// Step1
		// "\n 1st ===> setup Mail Server Properties.."
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		logger.info("Mail Server Properties have been setup successfully..");

		// Step2
		// 2nd ===> get Mail Session..";
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		for (String email : toList) {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		}
		if (ccList != null) {
			for (String email : ccList) {
				generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
			}
		}
		generateMailMessage.setSubject(subject);
		String emailBody = message + "<br><br> Regards, <br>Clarice Phelps Team";
		generateMailMessage.setContent(emailBody, "text/html");
		logger.info("Mail Session has been created successfully..");

		// Step3
		// "\n\n 3rd ===> Get Session and Send mail"
		Transport transport = getMailSession.getTransport("smtp");
		// Enter your correct gmail UserID and Password
		transport.connect("smtp.gmail.com", "claricetestautomation@gmail.com", "Claricetech!1");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		logger.info("Email sent successfully");
	}

	/**
	 * Writes email information into a file and save it in output directory.
	 * line 1: Recipient list
	 * list 2: Subject of the email
	 * line 3: Body of the email
	 * 
	 * @return the absolute file path and name
	 * @throws IOException
	 */
	public static String writeEmailIntoFile(String recipientList, String subject, String body, String path) throws IOException {
		String emailFileName = "emailMessageFile.txt";
		File emailFileObj = new File(path + "/" + emailFileName);
		FileWriter fw = new FileWriter(emailFileObj.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(recipientList);
		bw.newLine();
		bw.write(subject);
		bw.newLine();
		bw.write(body);
		bw.close();
		return emailFileObj.getAbsolutePath();
	}
}
