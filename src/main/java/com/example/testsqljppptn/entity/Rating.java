package com.example.testsqljppptn.entity;

import javax.persistence.*;

@Entity
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_rating;

    private float rating;

    @ManyToOne
    @JoinColumn(name="id_article")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    public Long getId_rating() {
        return id_rating;
    }

    public void setId_rating(Long id) {
        this.id_rating = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
