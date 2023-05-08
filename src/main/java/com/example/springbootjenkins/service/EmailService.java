package com.example.mailSender.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
@Service
public class EmailService {

    public void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com"); For Gmail purpose - add App passwords
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("saurabh.kohade@outlook.com", "Kohade12345@");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("saurabh.kohade@outlook.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("saurabh.kohade@outlook.com"));
        msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse("kohade@gmail.com"));
        msg.setSubject("Springboot mail sender");
//        msg.setContent("Mail content to be written here", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Message body part", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

//        attachPart.attachFile(new File("src/main/resources/git.png"));
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
