package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_customer;

    private String mail;

    private String password;

    private String name;

    private String firstName;

    private String address;

    private String city;

    private int postalCode;

    private boolean isAdmin;

    private String addressComment;
    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;
    @OneToMany(mappedBy = "id_contact")
    private Set<Contact> contacts;
    @OneToMany(mappedBy = "id_paiement_method")
    private Set<PaiementMethod> paiementMethod;
    @OneToMany(mappedBy = "id_order")
    private Set<Order> orders;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "favorites",joinColumns = {@JoinColumn(name = "id_customer")}, inverseJoinColumns = {@JoinColumn(name = "id_article")})
    private Set<Article> favorites;
    @OneToMany( mappedBy = "id_rating")
    private Set<Rating> ratings;

    public Customer(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public Customer() {

    }



    public String getAddressComment() {
        return addressComment;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public Long getId_customer() {
        return id_customer;
    }

    public void setId_customer(Long id) {
        this.id_customer = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
