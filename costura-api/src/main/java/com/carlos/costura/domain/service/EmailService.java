package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(Post postModel) throws MessagingException, IOException {
        List<String> emails = postModel.getUser().getFollowers().stream().map(f -> f.getFollowersPK().getFrom().getEmail()).collect(Collectors.toList());

        MimeMessage message = mailSender.createMimeMessage();
        String mailSubject = "Nova postagem - Molb!";
        String mailContent = "<b>Olá, trazemos uma novidade!</b><p>Nova postagem realizada no Molb! Venha conferir as novidades!</p>";
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("testecarlosgft@gmail.com");
        helper.setTo(emails.stream().toArray(String[]::new));
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }
}
