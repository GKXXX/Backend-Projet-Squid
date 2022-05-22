package com.example.testsqljppptn.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subject;


    private String message;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    public String getMessage() {
        return message;
    }

    public String getSujet() {
        return subject;
    }

    public Long getId_contact() {
        return id;
    }

    public void setId_contact(Long id) {
        this.id = id;
    }
}
