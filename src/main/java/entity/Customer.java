package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "mail", unique = true, length = 100)
    private String mail;
    @Column(name = "password", length = 50)
    private String password;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "firstName", length = 50)
    private String firstName;
    @Column(name = "address", length = 150)
    private String address;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name="postalCode")
    private int postalCode;
    @Column(name = "adressComment", length = 200)
    private String addressComment;
    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;
    @OneToMany(mappedBy = "customer")
    private Set<Contact> contacts;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="paiementMethodId",referencedColumnName = "id")
    private PaiementMethod paiementMethod;
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "favorite",joinColumns = {@JoinColumn(name = "customerId")}, inverseJoinColumns = {@JoinColumn(name = "articleId")})
    private Set<Article> favorites;
    @OneToMany( mappedBy = "customer")
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
