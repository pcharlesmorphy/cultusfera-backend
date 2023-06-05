package com.example.biblioteca.service;

import com.example.biblioteca.pojo.Mail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService{

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }


    @Override
    public void sendUserExpirationWarningMail(Mail mail) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(mail.getLibraryMail());
        mailMessage.setTo(mail.getUserMail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailSender.send(mailMessage);
    }




}
