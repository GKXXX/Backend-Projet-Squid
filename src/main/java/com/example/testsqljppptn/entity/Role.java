package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @OneToMany(mappedBy="role")
    private Set<Customer> customers;

    public String getName() {
        return name;
    }

    public Long getId_role() {
        return id;
    }

    public void setId_role(Long id) {
        this.id = id;
    }
}
