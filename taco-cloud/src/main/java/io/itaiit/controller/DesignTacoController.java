package io.itaiit.controller;

import io.itaiit.data.IngredientRepository;
import io.itaiit.data.TacoRepository;
import io.itaiit.domain.Ingredient;
import io.itaiit.domain.Order;
import io.itaiit.domain.Taco;
import io.itaiit.prop.TacoPageProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@EnableConfigurationProperties(TacoPageProp.class)
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    private final TacoPageProp tacoPageProp;


    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository, TacoPageProp tacoPageProp) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.tacoPageProp = tacoPageProp;
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

    @GetMapping("/recent")
    @ResponseBody
    public List<Taco> recentTacos() {
        Sort createdAtDesc = Sort.by("createdAt").descending();
        PageRequest pageRequest = PageRequest.of(tacoPageProp.getPage(), tacoPageProp.getSize(), createdAtDesc);
        return tacoRepository.findAll(pageRequest).getContent();
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
