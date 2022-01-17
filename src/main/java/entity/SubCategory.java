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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
