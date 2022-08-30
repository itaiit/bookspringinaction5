package io.itaiit.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * @author itaiit
 * @date 2022/8/23 15:46
 */
@Data
@Entity
@ToString
public class Ingredient {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Ingredient() {
    }

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
