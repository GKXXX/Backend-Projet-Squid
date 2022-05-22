package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

public class Cart {

    private Long id;

    private Set<Article> articles;

    public Cart(Long id, Set<Article> articles) {
        this.id = id;
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
