package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FavoriteRepository extends CrudRepository<Customer,Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO favorites VALUES (:idCustomer,:idArticle)",nativeQuery = true)
    void saveFavorite(@Param("idCustomer") int idCustomer,@Param("idArticle") int idArticle);

    
}
