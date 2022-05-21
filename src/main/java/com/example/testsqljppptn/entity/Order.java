package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_order;

    private Number totalAmmount;

    private String shipperyState;

    private String paiementMethod;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name="order_to_article",joinColumns = {@JoinColumn(name="id")}, inverseJoinColumns = {@JoinColumn(name="id_article")})
    private Set<Article> articles;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    private Customer customer;

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id) {
        this.id_order = id;
    }
}
