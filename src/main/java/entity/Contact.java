package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subject;
    @Lob

    private String message;
    @ManyToOne
    @JoinColumn(name = "id")
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
