package io.itaiit.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author itaiit
 * @date 2022/8/23 15:46
 */
@Data
@Table("ingredient")
@ToString
public class Ingredient {
    @PrimaryKey
    private String id;
    private String name;
    private Type type;

    public Ingredient() {
    }

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
