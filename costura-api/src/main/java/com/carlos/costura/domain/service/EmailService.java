package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailService {

    private static final String EMAIL_TIME = "carlos.marangoni1@gmail.com";

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

    @Async
    public void sendEmailToTeam(MessageDTO contactMessage, User user) throws MessagingException, IOException {
        List<String> emails =  new ArrayList<>();
        emails.add(EMAIL_TIME);

        MimeMessage message = mailSender.createMimeMessage();
        String mailSubject = "Solicitação de contato.";
        String mailContent = "<b>Olá, um novo cliente solicitou contato!</b><p>Mensagem: <br><b>"+ contactMessage.getMessage() +"</b></p><br>" +
                "<p>Dados do cliente:</p>" +
                "<p>Nome: "+user.getName() +"<br>" +
                "<p>E-mail: "+user.getEmail()+"<br>" +
                "<p>Usuário: "+user.getUser() +"<br>"
                +"</p>";
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("testecarlosgft@gmail.com");
        helper.setTo(emails.stream().toArray(String[]::new));
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }

    @Async
    public void sendEmailPasswordChangeRequest(MessageDTO contactMessage, User user) throws MessagingException, IOException {
        List<String> emails =  new ArrayList<>();
        emails.add(EMAIL_TIME);

        MimeMessage message = mailSender.createMimeMessage();
        String mailSubject = "Solicitação de alteração de senha.";
        String mailContent = "<b>Olá, um novo cliente solicitou a alteração de senha!</b><p>Mensagem: <br><b>"+ contactMessage.getMessage() +"</b></p><br>" +
                "<p>Dados do cliente:</p>" +
                "<p>Nome: "+user.getName() +"<br>" +
                "<p>E-mail: "+user.getEmail()+"<br>" +
                "<p>Usuário: "+user.getUser() +"<br>"
                +"</p>";
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("testecarlosgft@gmail.com");
        helper.setTo(emails.stream().toArray(String[]::new));
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }
}
