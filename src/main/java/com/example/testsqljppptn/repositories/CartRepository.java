package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends CrudRepository<Cart,Integer> {

    @Query(value = "INSERT INTO carts(article,customer) VALUES (:idArticle,:idCustomer)",nativeQuery = true)
    public void addPanier(@Param("idArticle") int idArticle,@Param("idCustomer") int idCustomer);

}
