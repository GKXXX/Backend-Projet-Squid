package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Article {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="stock")
    private int stock;
    @Column(name="color")
    private String color;
    @Column(name="price")
    private Number price;
    @OneToMany(mappedBy = "article")
    private Set<Image> images;
    @ManyToMany(mappedBy = "articles")
    private Set<SubCategory> subCategories;
    @ManyToMany(mappedBy ="articles")
    private Set<Order> orders;
    @ManyToMany(mappedBy = "favorites")
    private Set<Customer> favorites;
    @OneToMany(mappedBy = "article")
    private Set<Rating> ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

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

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
