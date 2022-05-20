package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,Long> {

    @Override
    <S extends Order> S save(S entity);

    @Override
    <S extends Order> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Order> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Order> findAll();

    @Override
    Iterable<Order> findAllById(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Order entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Order> entities);

    @Override
    void deleteAll();
}
