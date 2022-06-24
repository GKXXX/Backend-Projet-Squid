package com.example.testsqljppptn.repositories;

import com.example.testsqljppptn.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

    @Override
    <S extends Order> S save(S entity);

    @Override
    <S extends Order> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Order> findById(Integer aLong);

    @Override
    boolean existsById(Integer aLong);

    @Override
    Iterable<Order> findAll();

    @Override
    Iterable<Order> findAllById(Iterable<Integer> longs);

    @Override
    long count();

    @Override
    void deleteById(Integer aLong);

    @Override
    void delete(Order entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> longs);

    @Override
    void deleteAll(Iterable<? extends Order> entities);

    @Override
    void deleteAll();

    @Query(value = "SELECT * FROM orders WHERE customer=:idCustomer",nativeQuery = true)
    Iterable<Order> getMyOrder(@Param("idCustomer") int idCustomer);
}
