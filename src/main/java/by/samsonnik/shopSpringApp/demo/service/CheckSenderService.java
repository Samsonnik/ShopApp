package by.samsonnik.shopSpringApp.demo.service;

import by.samsonnik.shopSpringApp.demo.other.PassAuthForCheckSenderService;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class CheckSenderService {

    private final String USER_NAME = "vanyaa22@yandex.by";
    private final String PASSWORD = "fskfbtgmltvvuicm";

    public void sendMail(String messageText, String mailAddress) {
        Session session = Session.getInstance(getPropertiesForSending(), new PassAuthForCheckSenderService(USER_NAME, PASSWORD));
        Message message = makeMessage(session, messageText, mailAddress);
        try {
            Transport.send(message);
        } catch (MessagingException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static Properties getPropertiesForSending() {
        Properties propertiesForSending = new Properties();
        propertiesForSending.put("mail.smtp.host", "smtp.yandex.ru");
        propertiesForSending.put("mail.smtp.port", "465");
        propertiesForSending.put("mail.smtp.auth", "true");
        propertiesForSending.put("mail.smtp.starttls.enable", "true");
        propertiesForSending.put("mail.smtp.ssl.protocols", "TLSv1.2");
        propertiesForSending.put("mail.smtp.ssl.trust", "smtp.yandex.ru");
        propertiesForSending.put("mail.smtp.ssl.enable", "true");
        return propertiesForSending;
    }

    private static Message makeMessage(Session session, String messageText, String mailAddress) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("vanyaa22@yandex.by"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailAddress)
            );
            message.setSubject("Your check. Thanks:)");
            message.setText(messageText);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return message;
    }
}
