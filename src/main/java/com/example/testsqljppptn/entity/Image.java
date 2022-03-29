package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_image;

    private String url;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_article")
    private Article article;

    public Image() {
    }

    public Image(Long id_image, String url, Article article) {
        this.id_image = id_image;
        this.url = url;
        this.article = article;
    }

    public Image(String url, Article article) {
        this.url = url;
        this.article = article;
    }

    public Long getId_image() {
        return id_image;
    }

    public void setId_image(Long id) {
        this.id_image = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
