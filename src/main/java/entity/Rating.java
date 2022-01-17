package entity;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "rating")
    private float rating;
    @ManyToOne
    @JoinColumn(name="articleId")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
