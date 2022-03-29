package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Image;
import com.example.testsqljppptn.repositories.ArticleRepository;
import com.example.testsqljppptn.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping()
    public Iterable<Image> getImage() {
        return imageRepository.findAll();
    }

    @GetMapping("/insertData")
    public String InsertFalseData() {
        imageRepository.save(new Image("https://www.ikea.com/fr/fr/images/products/taernoe-table-exterieur-noir-teinte-brun-clair__0735751_pe740159_s5.jpg?f=xl",articleRepository.findById(1).get()));
        return "done";
    }
}
