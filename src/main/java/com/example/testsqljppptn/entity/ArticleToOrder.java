package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;

@Entity
@Table(name = "article_to_order")
public class ArticleToOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "article",referencedColumnName = "id")
    private Article article;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "orderr",referencedColumnName = "id")
    private Order order;

    private int quantity;

    private Long priceWhenBuying;

    private String nameArticle;

    private String descriptionArticle;

    private String imagePresArticle;

    private int idArticle;

    public ArticleToOrder() {
    }

    public ArticleToOrder(Article article, Order order, int quantity, Long actualPrice) {
        this.article = article;
        this.order = order;
        this.quantity = quantity;
        this.priceWhenBuying = actualPrice;
        this.nameArticle = this.article.getName();
        this.descriptionArticle = this.article.getDescription();
        this.imagePresArticle = this.article.getImages().get(1).getUrl();
        this.idArticle = this.article.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPriceWhenBuying() {
        return priceWhenBuying;
    }

    public void setPriceWhenBuying(Long priceWhenBuying) {
        this.priceWhenBuying = priceWhenBuying;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

    public String getImagePresArticle() {
        return imagePresArticle;
    }

    public void setImagePresArticle(String imagePresArticle) {
        this.imagePresArticle = imagePresArticle;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
}
