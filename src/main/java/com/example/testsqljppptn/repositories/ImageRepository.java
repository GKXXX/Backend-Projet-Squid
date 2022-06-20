package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Image;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
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

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO images(url,article) VALUES (:url,:idArticle)",nativeQuery = true)
    void customSave(@Param("url") String url,@Param("idArticle") int idArticle);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM images WHERE article = :idArticle",nativeQuery = true)
    void deleteImagesByArticle(@Param("idArticle") int idArticle);
}
