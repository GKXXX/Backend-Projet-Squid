package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_article;

    private String name;

    private String description;

    private int stock;

    private String color;

    private Long price;
    @OneToMany(mappedBy = "id_image",cascade = CascadeType.ALL)
    private Set<Image> images;
    @ManyToMany(mappedBy ="articles")
    private Set<Order> orders;
    @ManyToMany(mappedBy = "favorites")
    private Set<Customer> favorites;
    @OneToMany(mappedBy = "id_rating",cascade = CascadeType.ALL)
    private Set<Rating> ratings;


    public Article() {
    }

    public Article(int id_article, String name, String description, int stock, String color, Long price, Set<Image> images) {
        this.id_article = id_article;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
        this.images = images;
    }

    public Article(int id_article, String name, String description, int stock, String color, Long price, Set<Image> images, Set<Rating> ratings) {
        this.id_article = id_article;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
        this.images = images;
        this.ratings = ratings;
    }

    public Article(String name, String description, int stock, String color, Long price) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
    }

    public Article(String name, String description, int stock, String color, Long price, Set<Image> images) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
        this.images = images;
    }






    public int getId() {
        return id_article;
    }

    public void setId(int id_article) {
        this.id_article = id_article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
/**
    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Customer> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Customer> favorites) {
        this.favorites = favorites;
    }
    */
    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

}
