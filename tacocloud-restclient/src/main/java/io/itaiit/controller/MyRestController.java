package io.itaiit.controller;

import io.itaiit.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author itaiit
 * @date 2022/8/30 19:50
 */
@RestController
@RequestMapping("rest")
public class MyRestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ingredient/{id}")
    public Ingredient getIngredientById(@PathVariable("id") String id) {
        Ingredient ingredient = restTemplate.getForObject(
                "http://localhost:8080/api/ingredients/{id}",
                Ingredient.class,
                id
        );
        return ingredient;
    }

}
