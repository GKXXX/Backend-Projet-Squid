package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float rating;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_article",referencedColumnName = "id")
    private Article article;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_customer",referencedColumnName = "id")
    private Customer customer;

    public Rating(float rating, Article article, Customer customer) {
        this.rating = rating;
        this.article = article;
        this.customer = customer;
    }

    public Rating() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
