package com.example.testsqljppptn.repositories;


import com.example.testsqljppptn.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Integer> {

    @Override
    <S extends Contact> S save(S entity);
}
