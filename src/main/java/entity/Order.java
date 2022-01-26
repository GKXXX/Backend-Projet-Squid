package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Number totalAmmount;

    private String shipperyState;

    private String paiementMethod;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="order_to_article",joinColumns = {@JoinColumn(name="id")}, inverseJoinColumns = {@JoinColumn(name="id_article")})
    private Set<Article> articles;
    @ManyToOne
    @JoinColumn(name="id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
