package com.example.testsqljppptn.controllers;


import com.example.testsqljppptn.entity.Contact;
import com.example.testsqljppptn.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    /**
     * Ajoute un objet Contact à la base de donnée
     * @param contact
     * @return
     */
    @PostMapping()
    public ResponseEntity postContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok("Demande de contact envoyée.");
    }

}
