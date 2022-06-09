package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
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

    //@Query("SELECT articles.id_article, articles.color,articles.description,articles.name,articles.price FROM articles JOIN ")

    @Query(value = "select DISTINCT articles.id,articles.color,articles.description,articles.name,articles.price,articles.stock FROM articles INNER JOIN article_to_category ON articles.id_article = article_to_category.id_article WHERE article_to_category.id_category = :idCategory", nativeQuery = true)
    Iterable<Article> findByCategory(@Param("idCategory") Long idCategory);

    @Query(value = "SELECT id_article,avg(rating) as averageRating FROM ratings GROUP BY id_article ORDER BY averageRating DESC ",nativeQuery = true)
    Object[][] findAverageRatings();
}
