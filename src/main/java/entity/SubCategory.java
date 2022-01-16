package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SubCategory {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="SubCategoryToProduct",
            joinColumns = {@JoinColumn(name="subCategoryId")},
            inverseJoinColumns = {@JoinColumn(name="articleId")})
    private Set<Article> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
