package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    private String description;

    private int stock;

    private String color;

    private Float price;
    @OneToMany(mappedBy = "article",cascade = CascadeType.PERSIST)
    private Set<Image> images;
    @JsonIgnore
    @ManyToMany(mappedBy ="articles",fetch = FetchType.EAGER)
    private Set<Order> orders;
    @JsonIgnore
    @ManyToMany(mappedBy = "favorites",fetch = FetchType.EAGER)
    private Set<Customer> favorites;
    @OneToMany(mappedBy = "rating",cascade = CascadeType.MERGE)
    private Set<Rating> ratings;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "category",referencedColumnName = "id")
    private Category category;


    public Article() {
    }

    public Article(int id_article, String name, String description, int stock, String color, Float price, Set<Image> images) {
        this.id = id_article;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
        this.images = images;
    }

    public Article(int id_article, String name, String description, int stock, String color, Float price, Set<Image> images, Set<Rating> ratings) {
        this.id = id_article;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
        this.images = images;
        this.ratings = ratings;
    }

    public Article(String name, String description, int stock, String color, Float price) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
    }

    public Article(String name, String description, int stock, String color, Float price, Set<Image> images) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
        this.images = images;
    }







    public int getId() {
        return id;
    }

    public void setId(int id_article) {
        this.id = id_article;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
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

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categories) {
        this.category = categories;
    }
}
