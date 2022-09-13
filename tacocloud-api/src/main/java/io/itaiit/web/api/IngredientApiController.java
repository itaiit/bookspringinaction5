package io.itaiit.web.api;

import io.itaiit.data.IngredientRepository;
import io.itaiit.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author itaiit
 * @date 2022/9/2 12:12
 */
@RestController
@RequestMapping(value = "/ingredient", produces = "application/json")
public class IngredientApiController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("{id}")
    public Mono<Ingredient> getIngredientById(@PathVariable("id") String id) {
        Mono<Ingredient> byId = ingredientRepository.findById(id);
        return byId;
//        if (byId.isPresent()) {
//            return new ResponseEntity<Ingredient>(byId.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
    }

}
