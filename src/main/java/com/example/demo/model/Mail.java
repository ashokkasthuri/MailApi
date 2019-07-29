package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mail {

    @Id
    private String id = "";
    private String subject = "";
    private String body = "";


    public Mail() {
    }

    public Mail(String id, String title, String author) {
        this.id = id;
        this.subject = title;
        this.body = author;

    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;	
    }

    public String getBody() {
        return body;
    }


}
