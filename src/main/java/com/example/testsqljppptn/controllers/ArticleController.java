package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.testsqljppptn.repositories.ArticleRepository;

import javax.websocket.server.PathParam;
import java.util.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping()
    public @ResponseBody ResponseEntity addNewArticle(@RequestBody Article article) {
        articleRepository.save(article);
        return ResponseEntity.ok().body("article created.");
    }

    /**@GetMapping("/trending")
    public @ResponseBody Iterable<Article> getTrending(){
        ArrayList<Article> listArticle = (ArrayList<Article>) articleRepository.findAll();
        ArrayList<Article> listTrending = new ArrayList<Article>();
        HashMap<Integer, Float> listMoyenneRating = new HashMap<Integer,Float>();

        listArticle.forEach(article -> {
            float moyenne = 0;

            if (!article.getRatings().isEmpty()) {
                Object[] listRating = article.getRatings();
                for (int i = 0;i < article.getRatings().size(); i++) {
                    moyenne = moyenne +
                }
            }

        });

    }*/

    @GetMapping()
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/getByCategory")
    public @ResponseBody Iterable<Article> getByCategory(@RequestParam Long idCategory) {
        System.out.println(idCategory);
        int id = idCategory.intValue();
        return articleRepository.findByCategory(id);
    }

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
    public @ResponseBody ResponseEntity editArticle(@RequestBody Article article) {
        Optional<Article > articleToEdit = articleRepository.findById(article.getId());
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
                articleToEdit.get().setCategory(article.getCategory());
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