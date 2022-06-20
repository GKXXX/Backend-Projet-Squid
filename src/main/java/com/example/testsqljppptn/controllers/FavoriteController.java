package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @PostMapping()
    public void postFavorite(@RequestParam("idCustomer") int idCustomer,@RequestParam("idArticle") int idArticle) {
        favoriteRepository.saveFavorite(idCustomer,idArticle);
    }

    @GetMapping()
    public void getFavoritesByCustomer(@RequestParam("idCustomer") int idCustomer) {

    }
}
