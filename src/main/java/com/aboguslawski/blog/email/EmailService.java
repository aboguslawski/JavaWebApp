package com.aboguslawski.blog.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender {

//    private final JavaMailSender mailSender;

//    @Override
//    @Async
//    public void send(String to, String email) {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(email, true);
//            helper.setTo(to);
//            helper.setSubject("Confirm your email");
//            helper.setFrom("blog@mail.com");
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            log.error("failed to send email", e);
//            throw new IllegalStateException("failed to send email");
//        }
//    }

    @Override
    @Async
    public void send(String to, String mail) {
        log.info("sending token to " + to);
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "aboguslawskimail@gmail.com";
        String password = "Admin12#";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, to, mail);

        try {
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("exception 2");
        }

    }

    private Message prepareMessage(Session session, String myAccountEmail, String recipient, String token) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("email");
            message.setText("hey " + token);
            return message;
        } catch (Exception e) {
            System.out.println("exception");
        }
        return null;
    }
}
