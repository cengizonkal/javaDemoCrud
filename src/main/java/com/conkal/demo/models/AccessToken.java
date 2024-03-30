package com.conkal.demo.models;

import jakarta.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "access_tokens")
public class AccessToken {
    @Id
    // auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "valid_until") // timestamp
    private Timestamp validUntil;

    //belongs to user
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }
}
