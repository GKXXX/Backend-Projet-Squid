package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
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

    private Long price;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    private List<Image> images;
    @JsonIgnore
    @ManyToMany(mappedBy ="articles",fetch = FetchType.EAGER)
    private Set<Order> orders;
    @JsonIgnore
    @OneToMany(mappedBy = "article",fetch = FetchType.EAGER)
    private Set<Favorite> favorites;
    @OneToMany(mappedBy = "rating",cascade = CascadeType.MERGE)
    private Set<Rating> ratings;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "category",referencedColumnName = "id")
    private Category category;


    public Article() {
    }

    public Article(int id, String label) {
        this.id = id;
        this.name = label;
    }

    public Article(int id_article, String name, String description, int stock, String color, Long price, List<Image> images) {
        this.id = id_article;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.price = price;
        this.images = images;
    }

    public Article(int id_article, String name, String description, int stock, String color, Long price, List<Image> images, Set<Rating> ratings) {
        this.id = id_article;
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

    public Article(String name, String description, int stock, String color, Long price, List<Image> images) {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }




    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
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
