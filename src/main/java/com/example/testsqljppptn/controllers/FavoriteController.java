package com.example.testsqljppptn.controllers;


import com.example.testsqljppptn.entity.Favorite;
import com.example.testsqljppptn.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Favorite> getFavoritesByCustomer(@RequestParam("idCustomer") int idCustomer) {
        return favoriteRepository.getFavoritesByIdCustomer(idCustomer);

    }
}
