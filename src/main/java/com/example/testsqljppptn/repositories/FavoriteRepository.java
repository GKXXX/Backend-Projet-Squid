package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.entity.Favorite;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite,Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO favorites(article,customer) VALUES (:idArticle,:idCustomer)",nativeQuery = true)
    void saveFavorite(@Param("idCustomer") int idCustomer,@Param("idArticle") int idArticle);

    @Query(value = "SELECT * FROM favorites INNER JOIN articles ON favorites.article = articles.id WHERE favorites.customer = :idCustomer",nativeQuery = true)
    List<Favorite> getFavoritesByIdCustomer(@Param("idCustomer") int idCustomer);
}
