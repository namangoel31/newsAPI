package com.airtribe.newsAPI.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class verificationToken {

    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private newsUser user;

    private Date expiryDate;

    public verificationToken() {
    }

    public verificationToken(String token, Long id, newsUser user, Date expiryDate) {
        this.token = token;
        this.id = id;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public newsUser getUser() {
        return user;
    }

    public void setUser(newsUser user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
