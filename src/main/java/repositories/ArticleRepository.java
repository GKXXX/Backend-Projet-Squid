package repositories;

import entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Integer> {
    @Override
    Iterable<Article> findAll();
}
