package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

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
    @OneToMany(mappedBy = "id")
    private Set<Contact> contacts;
    @OneToMany(mappedBy = "id")
    private Set<PaiementMethod> paiementMethod;
    @OneToMany(mappedBy = "id")
    private Set<Order> orders;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "favorites",joinColumns = {@JoinColumn(name = "id_customer")}, inverseJoinColumns = {@JoinColumn(name = "id_article")})
    private Set<Article> favorites;
    @OneToMany( mappedBy = "id")
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
        return id;
    }

    public void setId_customer(Long id) {
        this.id = id;
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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setAddressComment(String addressComment) {
        this.addressComment = addressComment;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setPaiementMethod(Set<PaiementMethod> paiementMethod) {
        this.paiementMethod = paiementMethod;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void setFavorites(Set<Article> favorites) {
        this.favorites = favorites;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
