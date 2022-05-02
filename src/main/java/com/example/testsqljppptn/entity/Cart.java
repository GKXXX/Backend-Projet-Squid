package com.example.testsqljppptn.entity;

import javax.persistence.*;
import java.util.Set;

public class Cart {

    private Long id_Cart;

    private Set<Article> articles;
}
