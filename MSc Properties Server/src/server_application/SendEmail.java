/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Dwayne
 */
public class SendEmail {

    private final String from;
    private final String password;

    private final String host;
    private final Properties properties;

    public SendEmail(String from, String password, String host) {
        this.from = from;   
        this.password = password;
        this.host = host;
        properties = System.getProperties();

        // Start tls
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Enable auth
        properties.setProperty("mail.smtp.auth", "true");

        // Setup mail server
        properties.setProperty("mail.smtp.host", this.host);
    }

    public void sendEmail(String to, String subject, String messageText) {
        // Get the default Session object.
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(this.from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(messageText);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}