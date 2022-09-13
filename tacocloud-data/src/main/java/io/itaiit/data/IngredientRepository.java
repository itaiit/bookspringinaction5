package io.itaiit.data;

import io.itaiit.domain.Ingredient;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface IngredientRepository extends ReactiveCassandraRepository<Ingredient, String> {
}
