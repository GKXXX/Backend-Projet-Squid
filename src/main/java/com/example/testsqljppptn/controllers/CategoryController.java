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

    @GetMapping("/byId")
    public Optional<Category> getById(@RequestParam Long id) {
        return categoryRepository.findById(id);
    }

    @PostMapping()
    public ResponseEntity addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok("Category added.");
    }

    @PutMapping()
    public ResponseEntity editCategory(@RequestParam Long id ,@RequestBody Category category) {
        Optional<Category> categoryToEdit =  categoryRepository.findById(id);
        if (categoryToEdit.isPresent()) {
            if (!categoryToEdit.get().getName().equals(category.getName())) {
                categoryToEdit.get().setName(category.getName());
            }
            categoryRepository.save(categoryToEdit.get());
            return ResponseEntity.ok("Category edited.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteCategory(@RequestParam Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
