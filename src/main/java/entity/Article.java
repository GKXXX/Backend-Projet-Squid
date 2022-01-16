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
}
