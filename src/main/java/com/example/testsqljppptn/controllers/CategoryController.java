package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.testsqljppptn.entity.Category;
import com.example.testsqljppptn.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Réécupère la lsite de toutes les catégories
     * @return
     */
    @GetMapping()
    public ResponseEntity getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    /**
     * Récupère la catégorie lié à l'id donné
     * @param id
     * @return
     */
    @GetMapping("/byId")
    public ResponseEntity getById(@RequestParam Long id) {
        return ResponseEntity.ok(categoryRepository.findById(id));
    }

    /**
     * Ajoute une nouvelle catégorie à la base de donnée
     * @param category
     * @param token
     * @return
     */
    @PostMapping()
    public ResponseEntity addCategory(@RequestBody Category category,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        categoryRepository.save(category);
        return ResponseEntity.ok("Category added.");
    }

    /**
     * Modifie une catégorie lié à l'id donné
     * @param id
     * @param category
     * @param token
     * @return
     */
    @PutMapping()
    public ResponseEntity editCategory(@RequestParam Long id ,@RequestBody Category category,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        Optional<Category> categoryToEdit =  categoryRepository.findById(id);
        if (categoryToEdit.isPresent()) {
            if (!categoryToEdit.get().getName().equals(category.getName())) {
                categoryToEdit.get().setName(category.getName());
            }
            categoryRepository.save(categoryToEdit.get());
            return ResponseEntity.ok("Category edited.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Supprime la catégorie liée à l'id donné
     * @param id
     * @param token
     * @return
     */
    @DeleteMapping()
    public ResponseEntity deleteCategory(@RequestParam Long id,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
