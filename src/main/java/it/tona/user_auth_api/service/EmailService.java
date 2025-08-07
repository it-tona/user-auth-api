package it.tona.user_auth_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.*;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String to, String token) {
        String subject = "Reset della tua password";
        String resetUrl = "https://user-auth-fe-production.up.railway.app/reset-password?token=" + token;

        String message = "Hai richiesto di resettare la password.\n\n" +
                "Clicca il link per resettarla:\n" + resetUrl + "\n\n" +
                "Se non sei stato tu, ignora questo messaggio.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("no-reply@example.com");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }
}
