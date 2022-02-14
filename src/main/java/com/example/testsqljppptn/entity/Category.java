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
    @OneToMany(mappedBy="id_sub_category")
    private Set<SubCategory> subCategories;


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
