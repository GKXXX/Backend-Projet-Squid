package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="totalAmmount")
    private Number totalAmmount;
    @Column(name="shipperyState")
    private String shipperyState;
    @Column(name ="paiementMethod")
    private String paiementMethod;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="orderToArticle",joinColumns = {@JoinColumn(name="orderId")}, inverseJoinColumns = {@JoinColumn(name="articleId")})
    private Set<Article> articles;
    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
