package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String mail;

    private String password;

    private String name;

    private String firstName;

    private String address;

    private String city;

    private int postalCode;

    private String addressComment;
    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;
    @OneToMany(mappedBy = "id_customer")
    private Set<Contact> contacts;
    @OneToMany(mappedBy = "id_customer")
    private Set<PaiementMethod> paiementMethod;
    @OneToMany(mappedBy = "id_customer")
    private Set<Order> orders;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "favorites",joinColumns = {@JoinColumn(name = "id_customer")}, inverseJoinColumns = {@JoinColumn(name = "id_article")})
    private Set<Article> favorites;
    @OneToMany( mappedBy = "id_customer")
    private Set<Rating> ratings;



    public String getAddressComment() {
        return addressComment;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
