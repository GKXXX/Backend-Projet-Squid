package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.*;
import com.example.testsqljppptn.repositories.CategoryRepository;
import com.example.testsqljppptn.repositories.ImageRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.testsqljppptn.repositories.ArticleRepository;

import java.util.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

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

    @PostMapping("/multiple")
    public @ResponseBody ResponseEntity addNewArticles(@RequestBody HashSet<Article> articles) {
        ArrayList<Image> listImageArticle = new ArrayList<>();
        for (Article article1:articles) {
            if (article1.getImages() != null) {
                for (Image image : article1.getImages()) {
                    image.setArticle(article1);
                    listImageArticle.add(image);
                }
                article1.setImages(listImageArticle);
            }
            articleRepository.save(article1);
        }

        return ResponseEntity.ok().body("articles created.");
    }

    @GetMapping("/listName")
    public @ResponseBody ResponseEntity<String> getListName(){

        String returnString = "[";
        ArrayList<Article> listArticle = (ArrayList<Article>) articleRepository.findAll();
        if (listArticle.size() > 0) {
            for (int i = 0; i <= listArticle.size(); i++) {
                if (i == listArticle.size()) {
                    returnString = returnString + "{\"id\":" + listArticle.get(i).getId() + ",\"label\":\"" + listArticle.get(i).getName() + "\"}";
                } else {
                    returnString = returnString + "{\"id\":" + listArticle.get(i).getId() + ",\"label\":\"" + listArticle.get(i).getName() + "\"},";
                }
            }
            returnString = returnString + "]";
            return ResponseEntity.ok().body(returnString);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/trending")
    public @ResponseBody ResponseEntity getTrending(){
        ArrayList<Article> listArticle = (ArrayList<Article>) articleRepository.findAll();
        if (listArticle.size() == 0) {
            String[] emptyTab = new String[0];
            return ResponseEntity.ok(emptyTab);
        }
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
            int range = listArticle.size();
            for (int o = 1;o < j;o++) {
                listArticleToSend.add(listArticle.get((int) (Math.random() * range)));
            }
        }
        return ResponseEntity.ok().body(listArticleToSend);
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
            if (article.getImages().size() != 0){
                System.out.println(article.getImages());
                imageRepository.deleteImagesByArticle(articleToEdit.get().getId());
                for (Image image:article.getImages()) {
                    imageRepository.customSave(image.getUrl(),articleToEdit.get().getId());
                }

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