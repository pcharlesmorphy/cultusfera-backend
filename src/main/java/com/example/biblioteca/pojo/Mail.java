package com.example.biblioteca.pojo;

import lombok.Data;

@Data
public class Mail {
    private String libraryMail;
    private String userMail;
    private String subject;
    private String message;
    private String username;
    private String titleResource;

    public Mail (String userMail,String subject,String message){
        this.libraryMail = "cultusfera@outlook.es";
        this.userMail = userMail;
        this.subject = subject;
        this.message = message;
    }
}
