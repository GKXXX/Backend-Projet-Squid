package entity;

import javax.persistence.*;

@Entity
public class PaiementMethod {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="type")
    private String type;
    @Column(name="number")
    private int number;
    @Column(name="expirationDate")
    private String expirationDate;
    @Column(name="name")
    private String nom;
    @Column(name="firstName")
    private String firstName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customerId",referencedColumnName = "id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumero() {
        return number;
    }

    public void setNumero(int numero) {
        this.number = numero;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
