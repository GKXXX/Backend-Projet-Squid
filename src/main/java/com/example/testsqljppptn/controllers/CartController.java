package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Cart;
import com.example.testsqljppptn.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping()
    public void addToCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer) {
        cartRepository.addToCart(idArticle,idCustomer,1);
    }

    @PutMapping()
    public void modifyQuantity(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer,@RequestParam("quantity") int quatity) {
        cartRepository.updatePanier(idArticle,idCustomer,quatity);
    }

    @DeleteMapping()
    public void deleteArticleInCart(@RequestParam("idArticle") int idArticle,@RequestParam("idCustomer") int idCustomer) {
        cartRepository.deleteArticleInPanier(idArticle,idCustomer);
    }
}
