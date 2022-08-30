package io.itaiit.controller;

import io.itaiit.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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

    /**
     * 创建一个新的Ingredient
     *
     * @param ingredient
     * @return 新创建的Ingredient的资源链接："http://localhost:8080/api/ingredients/np01"
     */
    @PostMapping("ingredient")
    public URI createIngredient(@RequestBody Ingredient ingredient) {
        Traverson traverson = new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
        String href = traverson.follow("ingredients").asLink().getHref();
        URI uri = restTemplate.postForLocation(
//                "http://localhost:8080/api/ingredients",
                href,
                ingredient
        );
        return uri;
    }

}
