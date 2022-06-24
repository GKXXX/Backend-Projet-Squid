package com.example.testsqljppptn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long totalAmmount;

    private String shipperyState;

    private String shippingAddress;

    private String names;

    private Date deliveryDate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ArticleToOrder> articlesToOrder;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer",referencedColumnName = "id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTotalAmmount() {
        return totalAmmount;
    }

    public void setTotalAmmount(Long totalAmmount) {
        this.totalAmmount = totalAmmount;
    }

    public String getShipperyState() {
        return shipperyState;
    }

    public void setShipperyState(String shipperyState) {
        this.shipperyState = shipperyState;
    }

    public Set<ArticleToOrder> getArticlesToOrder() {
        return articlesToOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setArticlesToOrder(Set<ArticleToOrder> articlesToOrder) {
        this.articlesToOrder = articlesToOrder;
    }
}
