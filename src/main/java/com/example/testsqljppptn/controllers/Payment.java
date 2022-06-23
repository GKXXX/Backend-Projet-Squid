package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.testsqljppptn.entity.Article;
import com.example.testsqljppptn.entity.Cart;
import com.example.testsqljppptn.repositories.ArticleRepository;
import com.example.testsqljppptn.repositories.CartRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class Payment {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ArticleRepository articleRepository;

    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    private static final String stripeSecretKey = "sk_test_51LDUVDIvLK6IY2HZoJnP6rxcvKcV2GHvgzuZ48wOsigB0cEZb3ihUb2WRIVw1a2UiQbZ67QS9JOHRh53C3m6Xtgm00ZxVlpV6A";

    @PostMapping("/create-payment-intent")
    public ResponseEntity createPayment(@RequestParam("idCustomer") int idCustomer, @RequestHeader("token") String token) throws StripeException {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("Token d'authentification invalide");
        }

        Stripe.apiKey = stripeSecretKey;
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder().setAmount(getTotalAmount(idCustomer) * 100).setCurrency("eur").setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build()).build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        Map<String,Object> clientSecret = new HashMap<>();
        clientSecret.put("clientSecret",paymentIntent.getClientSecret());
        updateStock(idCustomer);
        return ResponseEntity.ok(clientSecret);

    }

    private Long getTotalAmount(int idCustomer) {
        ArrayList<Cart> listCart = (ArrayList<Cart>) cartRepository.getCartByCustomer(idCustomer);
        Long totalAmount = 0L;
        for (Cart cart:listCart) {
            totalAmount += cart.getArticle().getPrice() * cart.getQuantity();
        }
        return totalAmount;
    }

    private void updateStock(int idCustomer) {
        ArrayList<Cart> listCart = (ArrayList<Cart>) cartRepository.getCartByCustomer(idCustomer);
        for (Cart cart:listCart) {
            cart.getArticle().setStock(cart.getArticle().getStock() - cart.getQuantity());
            articleRepository.save(cart.getArticle());
        }
    }
}
