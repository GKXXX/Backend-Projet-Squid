package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.*;
import com.example.testsqljppptn.repositories.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.testsqljppptn.repositories.ArticleRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.PathParam;
import java.util.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping()
    public @ResponseBody ResponseEntity addNewArticle(@RequestBody Article article) {
        ArrayList<Image> listImageArticle = new ArrayList<>();
        if (article.getImages() != null) {
            for (Image image : article.getImages()) {
                image.setArticle(article);
                listImageArticle.add(image);
            }
            article.setImages(listImageArticle);
        }


        articleRepository.save(article);
        return ResponseEntity.ok().body("article created.");
    }

    @GetMapping("/listName")
    public @ResponseBody Object[][] getListName(){
        return articleRepository.findListNameArticle();
    }

    @GetMapping("/trending")
    public @ResponseBody Iterable<Article> getTrending(){
        Object[][] listAverageRatings = articleRepository.findAverageRatings();
        ArrayList<Article> listArticleToSend = new ArrayList<Article>();
        System.out.println(listAverageRatings.length);
        if (listAverageRatings.length >= 9) {
            for (int i = 1; i < 9; i++) {
                listArticleToSend.add(articleRepository.findById((Integer) listAverageRatings[i][0]).get());
            }
        } else {
            int sizeProduct = listAverageRatings.length;
            for (int i = 1; i<listAverageRatings.length;i++) {
                listArticleToSend.add(articleRepository.findById((Integer) listAverageRatings[i][0]).get());
            }
            int j = 9 - listArticleToSend.size();
            ArrayList<Article> listArticle = (ArrayList<Article>) articleRepository.findAll();
            int range = listArticle.size();
            for (int o = 1;o < j;o++) {
                listArticleToSend.add(listArticle.get((int) (Math.random() * range)));
            }
        }
        return listArticleToSend;
    }

    @JsonIgnore
    @GetMapping()
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/getByCategory")
    public @ResponseBody Iterable<Article> getByCategory(@RequestParam Long id) {
        return articleRepository.findByCategory(id);
    }

    @JsonIgnore
    @GetMapping("/byId")
    public @ResponseBody
    Object getArticleById(@RequestParam("id") Optional<Integer> id) {
        if (id.isPresent()) {
            return articleRepository.findById(id.get());
        } else {
            return "Id not specified";
        }
    }

    @PutMapping()
    public @ResponseBody ResponseEntity editArticle(@RequestParam int id,@RequestBody Article article) {
        Optional<Article > articleToEdit = articleRepository.findById(id);
        if (articleToEdit.isPresent()) {
            if (article.getName() != null) {
                articleToEdit.get().setName(article.getName());
            }
            if (article.getDescription() != null) {
                articleToEdit.get().setDescription(article.getDescription());
            }
            if (article.getColor() != null) {
                articleToEdit.get().setColor(article.getDescription());
            }
            if (article.getPrice() != null) {
                articleToEdit.get().setPrice(article.getPrice());
            }
            if (article.getStock() != 0) {
                articleToEdit.get().setStock(article.getStock());
            }
            if (article.getImages() != null){
                articleToEdit.get().setImages(article.getImages());
            }
            if(article.getCategory() != null ) {
                Optional<Category> category = categoryRepository.findById(article.getCategory().getId());
                category.ifPresent(value -> articleToEdit.get().setCategory(value));
            }
            articleRepository.save(articleToEdit.get());
            return ResponseEntity.ok("Article edited.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping()
    public ResponseEntity deleteArticle(@RequestParam("id") int id){
        articleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}