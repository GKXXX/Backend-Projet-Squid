package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Cart;
import com.example.testsqljppptn.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/byCustomer")
    public List<Cart> getByCustomer(@RequestParam("idCustomer") int idCustomer) {
        return cartRepository.getCartByCustomer(idCustomer);
    }

    @PostMapping("/withQuantity")
    public ResponseEntity addToCartWithQuantity(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestParam("quantity") int quantity) {
        cartRepository.addToCart(idArticle,idCustomer,quantity);
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
