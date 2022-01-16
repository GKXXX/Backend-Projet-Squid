package controllers;

import entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import repositories.ArticleRepository;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class ArticleController {

    private static final String template = "Hello World";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/getAll")
    public HashMap getAllArticles() {

        HashMap hmap = new HashMap();
        hmap.put("Text", "Salut mec");
        return hmap;
    }
}
