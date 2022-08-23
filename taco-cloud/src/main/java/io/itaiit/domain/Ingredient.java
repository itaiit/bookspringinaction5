package io.itaiit.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author itaiit
 * @date 2022/8/23 15:46
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;
    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
