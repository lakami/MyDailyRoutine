package service;

import common.GetLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender, GetLogger {

    @Autowired
    public EmailService(JavaMailSender mailSender, @Value("${mydailyroutine.mail.from}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    private final String from;
    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(from);
            mailSender.send(mimeMessage);

        } catch (MessagingException e){
            getLogger().error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }

    }
}
