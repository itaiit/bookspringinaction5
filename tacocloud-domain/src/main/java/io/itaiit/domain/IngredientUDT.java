package io.itaiit.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * @author itaiit
 * @date 2022/9/13 9:15
 */
@UserDefinedType("ingredient")
@Data
public class IngredientUDT {
    private String name;
    private Ingredient.Type type;
}
