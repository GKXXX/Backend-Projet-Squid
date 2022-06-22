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

    @GetMapping("/byCustomer")
    public ResponseEntity getByCustomer(@RequestParam("idCustomer") int idCustomer, @RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("Token d'authentification invalide");
        }
        return ResponseEntity.ok(cartRepository.getCartByCustomer(idCustomer));
    }

    @PostMapping("/withQuantity")
    public ResponseEntity addToCartWithQuantity(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestParam("quantity") int quantity) {
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

    @PostMapping("/increment")
    public ResponseEntity incrementCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer) {
        Optional<Cart> cart = cartRepository.getCartByCustomerAndArticle(idCustomer,idArticle);
        if (cart.isEmpty()) {
            cartRepository.addToCart(idArticle,idCustomer,1);
            return ResponseEntity.ok().build();
        }
        cart.get().setQuantity(cart.get().getQuantity() + 1);
        cartRepository.save(cart.get());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/decrement")
    public ResponseEntity decrementCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer) {
        Optional<Cart> cart = cartRepository.getCartByCustomerAndArticle(idCustomer,idArticle);
        if(cart.get().getQuantity() == 1) {
            cartRepository.delete(cart.get());
            return ResponseEntity.ok().build();
        }
        cart.get().setQuantity(cart.get().getQuantity() -1);
        cartRepository.save(cart.get());
        return ResponseEntity.ok().build();

    }

    @PutMapping()
    public ResponseEntity modifyQuantity(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestParam("quantity") int quatity) {
        cartRepository.updatePanier(idArticle,idCustomer,quatity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity deleteArticleInCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer) {
        cartRepository.deleteArticleInPanier(idArticle,idCustomer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/byCustomer")
    public ResponseEntity deleteCartByCustomer(@RequestParam("idCustomer") int idCustomer) {
        cartRepository.deleteCartByCustomer(idCustomer);
        return ResponseEntity.ok().build();
    }


}
