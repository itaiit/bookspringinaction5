package io.itaiit.controller;

import io.itaiit.data.IngredientRepository;
import io.itaiit.data.TacoRepository;
import io.itaiit.domain.Ingredient;
import io.itaiit.domain.Order;
import io.itaiit.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author itaiit
 * @date 2022/8/23 16:03
 */
@Slf4j
@Controller
@RequestMapping("design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        log.info("autowired：" + ingredientRepository);
    }

    @GetMapping
    public String addIngredientsToModel(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(item -> ingredients.add(item));

        Ingredient.Type[] types = Ingredient.Type.values();
        log.info("Ingredient types: " + Arrays.toString(types));
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @PostMapping
    public String processDesign(Taco design, @ModelAttribute("order") Order order) {
        log.info("Processing design: " + design + "; order: " + order);

        Taco save = tacoRepository.save(design);
        order.addDesign(save);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
