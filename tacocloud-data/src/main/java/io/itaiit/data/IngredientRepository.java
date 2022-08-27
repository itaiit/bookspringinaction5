package io.itaiit.data;

import io.itaiit.domain.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);

    Ingredient findOne(String id);

}
