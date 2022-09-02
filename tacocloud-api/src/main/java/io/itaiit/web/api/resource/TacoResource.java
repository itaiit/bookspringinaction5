package io.itaiit.web.api.resource;

import io.itaiit.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

/**
 * @author itaiit
 * @date 2022/8/28 14:40.
 */
@Getter
@Relation(itemRelation = "taco", collectionRelation = "tacos")
public class TacoResource extends RepresentationModel<TacoResource> {

    private Date createdAt;
    private String name;
    private CollectionModel<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.createdAt = taco.getCreatedAt();
        this.name = taco.getName();
        IngredientResourceAssembler resourceAssembler = new IngredientResourceAssembler();
        this.ingredients = resourceAssembler.toCollectionModel(taco.getIngredients());
    }
}
