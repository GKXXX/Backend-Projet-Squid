package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image,Long> {

    @Override
    <S extends Image> S save(S entity);

    @Override
    <S extends Image> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Image> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Image> findAll();

    @Override
    Iterable<Image> findAllById(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Image entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Image> entities);

    @Override
    void deleteAll();
}
