package com.example.testsqljppptn.entity;

import javax.persistence.*;

@Entity
@Table(name="paiement_methods")
public class PaiementMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_paiement_method;

    private String type;

    private int number;

    private String expirationDate;

    private String nom;

    private String firstName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_customer",referencedColumnName = "id_customer")
    private Customer customer;

    public Long getId_paiement_method() {
        return id_paiement_method;
    }

    public void setId_paiement_method(Long id) {
        this.id_paiement_method = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumero() {
        return number;
    }

    public void setNumero(int numero) {
        this.number = numero;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
