package com.example.testsqljppptn.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.testsqljppptn.entity.Favorite;
import com.example.testsqljppptn.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    /**
     * Ajoute un favori en base de donnée
     * @param idCustomer
     * @param idArticle
     * @param token
     * @return
     */
    @PostMapping()
    public ResponseEntity postFavorite(@RequestParam("idCustomer") int idCustomer,@RequestParam("idArticle") int idArticle,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        favoriteRepository.saveFavorite(idCustomer,idArticle);
        return ResponseEntity.ok("Favori ajouté.");
    }

    /**
     * Récupère les articles en favoris de l'utilisateur lié à l'id donné
     * @param idCustomer
     * @param token
     * @return
     */
    @GetMapping()
    public ResponseEntity getFavoritesByCustomer(@RequestParam("idCustomer") int idCustomer,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        return ResponseEntity.ok(favoriteRepository.getFavoritesByIdCustomer(idCustomer));

    }

    /**
     * Supprime un article des favoris de l'utilisateur lié à l'id donné
     * @param idCustomer
     * @param idArticle
     * @param token
     * @return
     */
    @DeleteMapping()
    public ResponseEntity deleteFavorite(@RequestParam("idCustomer") int idCustomer,@RequestParam("idArticle") int idArticle,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        favoriteRepository.deleteFavoriteByCustomerAndArticle(idCustomer,idArticle);
        return ResponseEntity.ok("Favori supprimé.");
    }
}
