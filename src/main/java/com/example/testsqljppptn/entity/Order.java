package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name="order_to_article",joinColumns = {@JoinColumn(name="id_order")}, inverseJoinColumns = {@JoinColumn(name="id_article")})
    private Set<Article> articles;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_customer",referencedColumnName = "id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotalAmmount() {
        return totalAmmount;
    }

    public void setTotalAmmount(Float totalAmmount) {
        this.totalAmmount = totalAmmount;
    }

    public String getShipperyState() {
        return shipperyState;
    }

    public void setShipperyState(String shipperyState) {
        this.shipperyState = shipperyState;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
