package io.itaiit.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

/**
 * @author itaiit
 * @date 2022/9/13 9:23
 */
@Data
@UserDefinedType("taco")
public class TacoUDT {

    private String name;
    private List<IngredientUDT> ingredients;

}

