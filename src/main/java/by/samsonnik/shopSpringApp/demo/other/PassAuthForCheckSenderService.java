package by.samsonnik.shopSpringApp.demo.other;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PassAuthForCheckSenderService extends Authenticator {
    private final String username;
    private final String password;

    public PassAuthForCheckSenderService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
