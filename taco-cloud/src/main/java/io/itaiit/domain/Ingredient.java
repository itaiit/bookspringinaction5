package io.itaiit.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author itaiit
 * @date 2022/8/23 15:46
 */
@Data
@Entity
public class Ingredient {
    @Id
    private String id;
    @Transient
    private Date createdAt = new Date();
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
