package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float totalAmmount;

    private String shipperyState;

    private String paiementMethod;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name="order_to_article",joinColumns = {@JoinColumn(name="id_order")}, inverseJoinColumns = {@JoinColumn(name="id_article")})
    private Set<Article> articles;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer")
    private Customer customer;

    public Long getId_order() {
        return id;
    }

    public void setId_order(Long id) {
        this.id = id;
    }
}
