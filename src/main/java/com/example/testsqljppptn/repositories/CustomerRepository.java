package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    @Override
    <S extends Customer> S save(S entity);

    @Override
    Optional<Customer> findById(Integer integer);

    @Override
    Iterable<Customer> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Customer entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Query(value = "SELECT * FROM customers c WHERE c.mail = :mail", nativeQuery = true)
    Optional<Customer> findByMail(@Param("mail") String mail);

    @Query(value = "SELECT * FROM customers",nativeQuery = true)
    Set<Customer> getAll();
}
