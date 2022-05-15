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

    @PostMapping("/postArticle")
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
    Object getArticleById(@PathParam("id") Optional<Integer> id) {
        if (id.isPresent()) {
            return articleRepository.findById(id.get());
        } else {
            return "Id not specified";
        }
    }
    @GetMapping("/createData")
    public @ResponseBody String createData() {
        Set<Image> listImage = new HashSet<Image>();
        listImage.add(new Image("https://www.ikea.com/fr/fr/images/products/taernoe-table-exterieur-noir-teinte-brun-clair__0735751_pe740159_s5.jpg?f=xl"));
        Article testArticle = new Article("test","description de test",1,"Blue",Long.valueOf(99),listImage);
        articleRepository.save(testArticle);
        return "Done.";
    }
}
