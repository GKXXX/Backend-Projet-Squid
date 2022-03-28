package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.testsqljppptn.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/postArticle")
    public @ResponseBody String addNewArticle(@RequestParam String name, @RequestParam String description, @RequestParam int stock, @RequestParam String color, @RequestParam Number price, @RequestParam ArrayList<Article> articles, @RequestParam SubCategory subCategory) {
        return null;
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<Article> getAllArticles() {
        //return articleRepository.findAll();
        return articleRepository.findAll();
    }
}
