package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart,Integer> {

    @Query(value = "SELECT * FROM carts INNER JOIN articles ON carts.article = articles.id WHERE carts.customer = :idCustomer",nativeQuery = true)
    public List<Cart> getCartByCustomer(@Param("idCustomer") int idCustomer);

    @Query(value = "SELECT * FROM carts WHERE customer = :idCustomer AND article = :idArticle",nativeQuery = true)
    public Optional<Cart> getCartByCustomerAndArticle(@Param("idCustomer") int idCustomer, @Param("idArticle") int idArticle);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO carts(article,customer,quantity) VALUES (:idArticle,:idCustomer,:quantity)",nativeQuery = true)
    public void addToCart(@Param("idArticle") int idArticle,@Param("idCustomer") int idCustomer,@Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query(value = "UPDATE carts SET quantity = :quantity WHERE article = :idArticle AND customer = :idCustomer",nativeQuery = true)
    public void updatePanier(@Param("idArticle") int idArticle,@Param("idCustomer") int idCustomer,@Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM carts WHERE article = :idArticle AND customer = :idCustomer",nativeQuery = true)
    public void deleteArticleInPanier(@Param("idArticle") int idArticle,@Param("idCustomer") int idCustomer);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM carts WHERE customer =:idCustomer",nativeQuery = true)
    void deleteCartByCustomer(@Param("idCustomer") int idCustomer);


}
