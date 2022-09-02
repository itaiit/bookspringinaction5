package io.itaiit.web.api.resource;

import io.itaiit.domain.Ingredient;
import io.itaiit.web.api.IngredientApiController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

/**
 * @author itaiit
 * @date 2022/9/2 11:52
 */
public class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(IngredientApiController.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toModel(Ingredient entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected IngredientResource instantiateModel(Ingredient entity) {
        return new IngredientResource(entity);
    }


}
