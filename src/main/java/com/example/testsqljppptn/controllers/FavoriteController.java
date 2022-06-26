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
