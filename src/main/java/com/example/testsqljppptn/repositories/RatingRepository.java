package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating,Long> {

    @Override
    <S extends Rating> S save(S entity);

    @Override
    <S extends Rating> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Rating> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Rating> findAll();

    @Override
    Iterable<Rating> findAllById(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Rating entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Rating> entities);

    @Override
    void deleteAll();
}
