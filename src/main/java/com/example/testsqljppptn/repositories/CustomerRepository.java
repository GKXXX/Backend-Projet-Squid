package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Article;
import com.example.testsqljppptn.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    @Override
    <S extends Customer> S save(S entity);

    @Override
    Optional<Customer> findById(Long integer);

    @Override
    Iterable<Customer> findAll();

    @Override
    void deleteById(Long integer);

    @Override
    void delete(Customer entity);

    @Override
    void deleteAllById(Iterable<? extends Long> integers);

    @Query(value = "SELECT * FROM customers c WHERE c.mail = :mail", nativeQuery = true)
    Optional<Customer> findByMail(@Param("mail") String mail);
}
