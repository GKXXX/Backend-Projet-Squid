package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name="id_category")
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="article_to_sub_category",
            joinColumns = {@JoinColumn(name="id_sub_category")},
            inverseJoinColumns = {@JoinColumn(name="id_article")})
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
