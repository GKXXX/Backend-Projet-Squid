package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_favorite;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "article",referencedColumnName = "id")
    private Article article;

    public Favorite() {
    }

    public Favorite(int id_favorite,  Article article) {
        this.id_favorite = id_favorite;
        this.article = article;
    }

    public int getId() {
        return id_favorite;
    }

    public void setId(int id_favorite) {
        this.id_favorite = id_favorite;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
