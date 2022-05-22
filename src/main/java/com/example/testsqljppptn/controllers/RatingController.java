package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Rating;
import com.example.testsqljppptn.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;

    @PostMapping()
    public ResponseEntity postRating(@RequestBody Rating rating) {
        System.out.println(rating.getArticle().getId());
        System.out.println(rating.getCustomer().getId());
        ratingRepository.save(rating);
        return ResponseEntity.ok().build();
    }
}
