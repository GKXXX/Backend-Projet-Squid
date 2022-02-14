package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article,Integer> {

    @Override
    <S extends Article> S save(S entity);

    @Override
    Optional<Article> findById(Integer integer);

    @Override
    Iterable<Article> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Article entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);
}
