package controllers;

import entity.Article;
import entity.Greeting;
import entity.SubCategory;
import org.springframework.web.bind.annotation.*;
import repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/articles")
public class ArticleController {



    private final ArticleRepository articleRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostMapping("/postArticle")
    public @ResponseBody
    String addNewArticle(@RequestParam String name, @RequestParam String description, @RequestParam int stock, @RequestParam String color, @RequestParam Number price, @RequestParam ArrayList<Article> articles, @RequestParam SubCategory subCategory) {
        return null;
    }



    @GetMapping("/greeting")
    public Greeting greeting() {
        return new Greeting(counter.incrementAndGet(), String.format(template, "Jass"));
    }

    @GetMapping("/get")
    public String getAllArticles() {
        try {
            return "hello world";
        } catch (Exception e) {
            return e.toString();
        }

       // return articleRepository.findAll();
        /*List<Article> hmap = new ArrayList<Article>();
        Article article = new Article();
        article.setId((long) 1);
        article.setName("RENBERGET");
        article.setDescription("Confortable, léger et facile à déplacer. La forme des accoudoirs ajoute au confort et le mécanisme de freinage des roulettes maintient le fauteuil en place lorsque vous vous levez et le libère lorsque vous vous asseyez.");
        article.setColor("Rouge");
        article.setPrice(40);
        SubCategory subCategory = new SubCategory();
        subCategory.setId((long) 1);
        subCategory.setName("Chaise de bureau");
        Category category = new Category();
        category.setId((long) 1);
        category.setName("Chaises");
        subCategory.setCategory(category);
        ArrayList<SubCategory> listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        ArrayList<Image> listImage = new ArrayList<Image>();
        Image image = new Image();
        image.setId((long) 1);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/renberget-chaise-pivotante-bomstad-noir__1020135_pe831794_s5.jpg?f=xxs");
        listImage.add(image);
        image.setId((long) 2);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/renberget-chaise-pivotante-bomstad-noir__1025981_pe834276_s5.jpg?f=xxs");
        listImage.add(image);
        image.setId((long) 3);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/renberget-chaise-pivotante-bomstad-noir__1025978_pe834273_s5.jpg?f=xxs");
        listImage.add(image);
        Rating rating = new Rating();
        rating.setId((long) 1);
        rating.setRating(5);
        ArrayList<Rating> listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long) 2);
        rating.setRating((float) 4.7);
        listRating.add(rating);
        hmap.add(article);

        article.setId((long) 2);
        article.setName("SÖDERHAMN");
        article.setDescription("SÖDERHAMN offre une assise profonde, basse et douce, avec des coussins de dossier souples pour un soutien supplémentaire.");
        article.setColor("Gris");
        article.setPrice(549);
        subCategory = new SubCategory();
        subCategory.setId((long) 2);
        subCategory.setName("Canapé modulable en tissu");
        category = new Category();
        category.setId((long) 1);
        category.setName("Canapé");
        subCategory.setCategory(category);
        listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        listImage = new ArrayList<Image>();
        image = new Image();
        image.setId((long) 4);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/soederhamn-module-3-places-viarp-beige-brun__0802813_pe768605_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 5);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/soederhamn-module-3-places-viarp-beige-brun__0802824_pe768608_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 6);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/soederhamn-module-3-places-viarp-beige-brun__0802812_pe768607_s5.jpg?f=s");
        listImage.add(image);
        rating = new Rating();
        rating.setId((long) 3);
        rating.setRating(4);
        listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long) 4);
        rating.setRating((float) 4.5);
        listRating.add(rating);
        hmap.add(article);

        article.setId((long) 3);
        article.setName("MALM");
        article.setDescription("La maison doit être un lieu sûr pour chaque membre de la famille. C'est pourquoi une fixation de sécurité est incluse afin de fixer la commode au mur.");
        article.setColor("Blanc");
        article.setPrice(169);
        subCategory = new SubCategory();
        subCategory.setId((long) 3);
        subCategory.setName("Commode");
        category = new Category();
        category.setId((long) 3);
        category.setName("Commodes et caissons à tiroir");
        subCategory.setCategory(category);
        listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        listImage = new ArrayList<Image>();
        image = new Image();
        image.setId((long) 7);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-commode-6-tiroirs-blanc__0484884_pe621348_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 8);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-commode-6-tiroirs-blanc__0823861_pe775996_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 9);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-commode-6-tiroirs-blanc__0823862_pe775997_s5.jpg?f=s");
        listImage.add(image);
        rating = new Rating();
        rating.setId((long) 5);
        rating.setRating(3);
        listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long) 6);
        rating.setRating((float) 3.7);
        listRating.add(rating);
        hmap.add(article);

        article.setId((long) 4);
        article.setName("SYVDE");
        article.setDescription("L’armoire ouverte SYVDE, avec tringles à habits et tablettes, est idéale pour ranger vos vêtements à portée de main, sans occuper trop d’espace. Elle se combine parfaitement avec la commode 6 tiroirs MALM.");
        article.setColor("Blanc");
        article.setPrice(90);
        subCategory = new SubCategory();
        subCategory.setId((long) 4);
        subCategory.setName("Armoire ouverte");
        category = new Category();
        category.setId((long) 4);
        category.setName("Armoires");
        subCategory.setCategory(category);
        listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        listImage = new ArrayList<Image>();
        image = new Image();
        image.setId((long) 10);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/syvde-armoire-ouverte-blanc__0720803_pe732860_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 11);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/syvde-armoire-ouverte-blanc__0720802_pe732862_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 12);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/syvde-armoire-ouverte-blanc__0720801_pe732859_s5.jpg?f=s");
        listImage.add(image);
        rating = new Rating();
        rating.setId((long) 6);
        rating.setRating(5);
        listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long)7);
        rating.setRating((float) 4.7);
        listRating.add(rating);
        hmap.add(article);

        article.setId((long) 5);
        article.setName("MALM");
        article.setDescription("Un design épuré. Esthétique de tous les côtés vous pouvez le placer au milieu d'une pièce ou avec la tête de lit contre le mur. Si vous avez besoin de place pour ranger des couettes ou des oreillers, ajoutez-y les rangements MALM sur roulettes.");
        article.setColor("Blanc");
        article.setPrice(449);
        subCategory = new SubCategory();
        subCategory.setId((long) 5);
        subCategory.setName("Cadre de lit double");
        category = new Category();
        category.setId((long) 5);
        category.setName("Cadre de lit");
        subCategory.setCategory(category);
        listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        listImage = new ArrayList<Image>();
        image = new Image();
        image.setId((long) 13);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-cadre-de-lit-haut-blanc-leirsund__0749130_pe745499_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 14);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-cadre-de-lit-haut-blanc-leirsund__0367617_pe549484_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 15);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-cadre-de-lit-haut-blanc-leirsund__0860700_pe662041_s5.jpg?f=s");
        listImage.add(image);
        rating = new Rating();
        rating.setId((long) 8);
        rating.setRating(2);
        listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long) 9);
        rating.setRating((float) 3.2);
        listRating.add(rating);
        hmap.add(article);

        article.setId((long) 6);
        article.setName("BESTÅ");
        article.setDescription("Le banc TV BESTÅ combine fonctionnalité et look moderne. Il offre un grand espace de rangement et permet de cacher les câbles qui ont tendance à s'emmêler et à récolter la poussière.");
        article.setColor("Blanc");
        article.setPrice(220);
        subCategory = new SubCategory();
        subCategory.setId((long) 6);
        subCategory.setName("Banc TV");
        category = new Category();
        category.setId((long) 6);
        category.setName("Meubles TV");
        subCategory.setCategory(category);
        listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        listImage = new ArrayList<Image>();
        image = new Image();
        image.setId((long) 16);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/besta-banc-tv-avec-portes-blanc-bergsviken-beige__0993145_pe820443_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 17);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/besta-banc-tv-avec-portes-blanc-bergsviken-beige__0843389_pe535717_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 18);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/besta-banc-tv-avec-portes-blanc-bergsviken-beige__0720087_pe732402_s5.jpg?f=s");
        listImage.add(image);
        rating = new Rating();
        rating.setId((long) 10);
        rating.setRating(4);
        listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long) 11);
        rating.setRating((float) 4.2);
        listRating.add(rating);
        hmap.add(article);

        article.setId((long) 7);
        article.setName("POÄNG");
        article.setDescription("40 ans après son lancement, ce fauteuil au design simple et intemporel a toujours autant de succès. Nous avons créé une taille adulte et une taille enfant pour satisfaire tous les âges. Vous avez également le choix entre divers coussins.");
        article.setColor("Marron");
        article.setPrice(229);
        subCategory = new SubCategory();
        subCategory.setId((long) 7);
        subCategory.setName("Fauteil en cuir");
        category = new Category();
        category.setId((long) 7);
        category.setName("Fauteuil");
        subCategory.setCategory(category);
        listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        listImage = new ArrayList<Image>();
        image = new Image();
        image.setId((long) 19);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/poaeng-fauteuil-brun-noir-glose-brun-fonce__55440_pe160526_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 20);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/poaeng-fauteuil-brun-noir-glose-brun-fonce__0837771_pe601094_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 21);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/poaeng-fauteuil-brun-noir-glose-brun-fonce__0250857_pe389394_s5.jpg?f=s");
        listImage.add(image);
        rating = new Rating();
        rating.setId((long) 12);
        rating.setRating(5);
        listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long) 13);
        rating.setRating((float) 4.7);
        listRating.add(rating);
        hmap.add(article);

        article.setId((long) 8);
        article.setName("MALM");
        article.setDescription("Il s'agit bien d'une coiffeuse, avec de la place pour ranger votre maquillage et vos bijoux. Mais elle peut aussi servir de bureau, de table d'appoint pour poser les clés ou le courrier dans l'entrée ou pour ranger des magazines derrière le canapé.");
        article.setColor("Blanc");
        article.setPrice(149);
        subCategory = new SubCategory();
        subCategory.setId((long) 8);
        subCategory.setName("Bureau");
        category = new Category();
        category.setId((long) 8);
        category.setName("Tables et bureaux");
        subCategory.setCategory(category);
        listSubCategory = new ArrayList<SubCategory>();
        article.setSubCategories((Set<SubCategory>) listSubCategory);
        listImage = new ArrayList<Image>();
        image = new Image();
        image.setId((long) 22);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-coiffeuse-blanc__0627084_pe693164_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 23);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-coiffeuse-blanc__0858727_pe555871_s5.jpg?f=s");
        listImage.add(image);
        image.setId((long) 24);
        image.setUrl("https://www.ikea.com/fr/fr/images/products/malm-coiffeuse-blanc__0379928_pe554970_s5.jpg?f=s");
        listImage.add(image);
        rating = new Rating();
        rating.setId((long) 14);
        rating.setRating(4);
        listRating = new ArrayList<Rating>();
        listRating.add(rating);
        rating.setId((long) 15);
        rating.setRating((float) 3.7);
        listRating.add(rating);
        hmap.add(article);

        return hmap;*/
    }
}
