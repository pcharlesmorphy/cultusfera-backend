package com.example.biblioteca.service;

import com.example.biblioteca.pojo.Mail;

public interface IEmailService {

    void sendUserExpirationWarningMail(Mail mail);

}
