package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String mail;

    private String password;

    private String name;

    private String firstName;

    private String address;

    private String city;

    private int postalCode;

    private boolean isAdmin;

    private String civility;

    private String addressComment;
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private Set<Contact> contacts;
    @OneToMany(mappedBy = "id")
    private Set<Order> orders;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Set<Favorite> favorites;
    @JsonIgnore
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

    public int getPostalCode() {
        return postalCode;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civilite) {
        this.civility = civilite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }


    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
