package entity;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="url")
    private String url;
    @ManyToOne
    @JoinColumn(name="articleId")
    private Article article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
