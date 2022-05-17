package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Category;
import com.example.testsqljppptn.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok("Category added.");
    }

    @PutMapping()
    public ResponseEntity editCategory(@RequestBody Category category) {
        Optional<Category> categoryToEdit =  categoryRepository.findById(category.getId_category());
        if (categoryToEdit.isPresent()) {
            if (!categoryToEdit.get().getName().equals(category.getName())) {
                categoryToEdit.get().setName(category.getName());
            }
            categoryRepository.save(category);
            return ResponseEntity.ok("Category edited.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
