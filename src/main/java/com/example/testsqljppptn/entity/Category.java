package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_category;

    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="article_to_category",
            joinColumns = {@JoinColumn(name="id_category")},
            inverseJoinColumns = {@JoinColumn(name="id_article")})
    private Set<Article> articles;


    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id) {
        this.id_category = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
