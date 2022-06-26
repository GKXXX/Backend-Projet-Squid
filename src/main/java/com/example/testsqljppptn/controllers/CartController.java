package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.testsqljppptn.entity.Cart;
import com.example.testsqljppptn.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    /**
     * Méthode renvoyant le panier lié à l'utilisateur lié à l'id passé en paramètre
     * @param idCustomer
     * @param token
     * @return
     */
    @GetMapping("/byCustomer")
    public ResponseEntity getByCustomer(@RequestParam("idCustomer") int idCustomer, @RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        return ResponseEntity.ok(cartRepository.getCartByCustomer(idCustomer));
    }

    /**
     * Méthode qui insère en base de donnée l'article lié à l'id passé en paramètre dans le panierde l'utilisateur liéé à l'id pass en paramètre avec la quantité passée en paramètre
     * @param idArticle
     * @param idCustomer
     * @param quantity
     * @param token
     * @return
     */
    @PostMapping("/withQuantity")
    public ResponseEntity addToCartWithQuantity(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestParam("quantity") int quantity,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        Optional<Cart> cart = cartRepository.getCartByCustomerAndArticle(idCustomer,idArticle);
        if (cart.isEmpty()) {
            cartRepository.addToCart(idArticle, idCustomer, quantity);
            return ResponseEntity.ok().build();
        } else {
            cartRepository.deleteArticleInPanier(idArticle,idCustomer);
            cartRepository.addToCart(idArticle,idCustomer,quantity);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Ajoute un article au panier d'un utilisateur ou augmente la quantité
     * @param idArticle
     * @param idCustomer
     * @param token
     * @return
     */
    @PostMapping("/increment")
    public ResponseEntity incrementCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        Optional<Cart> cart = cartRepository.getCartByCustomerAndArticle(idCustomer,idArticle);
        if (cart.isEmpty()) {
            cartRepository.addToCart(idArticle,idCustomer,1);
            return ResponseEntity.ok().build();
        }
        cart.get().setQuantity(cart.get().getQuantity() + 1);
        cartRepository.save(cart.get());
        return ResponseEntity.ok().build();
    }

    /**
     * Diminue la quanttitééé d'un article dans un panier ou le supprime si sa quantité est à 1
     * @param idArticle
     * @param idCustomer
     * @param token
     * @return
     */
    @PostMapping("/decrement")
    public ResponseEntity decrementCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        Optional<Cart> cart = cartRepository.getCartByCustomerAndArticle(idCustomer,idArticle);
        if(cart.get().getQuantity() == 1) {
            cartRepository.delete(cart.get());
            return ResponseEntity.ok().build();
        }
        cart.get().setQuantity(cart.get().getQuantity() -1);
        cartRepository.save(cart.get());
        return ResponseEntity.ok().build();

    }

    /**
     *Modifie la quantité d'un article dans un panier
     * @param idArticle
     * @param idCustomer
     * @param quatity
     * @param token
     * @return
     */
    @PutMapping()
    public ResponseEntity modifyQuantity(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestParam("quantity") int quatity,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        cartRepository.updatePanier(idArticle,idCustomer,quatity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity deleteArticleInCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        cartRepository.deleteArticleInPanier(idArticle,idCustomer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/byCustomer")
    public ResponseEntity deleteCartByCustomer(@RequestParam("idCustomer") int idCustomer,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        cartRepository.deleteCartByCustomer(idCustomer);
        return ResponseEntity.ok().build();
    }


}
