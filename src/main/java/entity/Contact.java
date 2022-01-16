package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Contact {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "subject", length = 150)
    private String subject;
    @Lob
    @Column(name="message")
    private String message;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public String getMessage() {
        return message;
    }

    public String getSujet() {
        return subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
