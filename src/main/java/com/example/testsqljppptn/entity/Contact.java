package com.example.testsqljppptn.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_contact;

    private String subject;


    private String message;

    @ManyToOne
    @JoinColumn(name = "idcustomer")
    private Customer customer;

    public String getMessage() {
        return message;
    }

    public String getSujet() {
        return subject;
    }

    public Long getId_contact() {
        return id_contact;
    }

    public void setId_contact(Long id) {
        this.id_contact = id;
    }
}
