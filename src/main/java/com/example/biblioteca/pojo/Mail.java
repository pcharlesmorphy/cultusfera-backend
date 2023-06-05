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

    public Mail (String userMail,String message){
        this.libraryMail = "cultusfera@outlook.es";
        this.userMail = userMail;
        this.subject = "Aviso: Vencimiento de préstamo";
        this.message = message;
    }
}
