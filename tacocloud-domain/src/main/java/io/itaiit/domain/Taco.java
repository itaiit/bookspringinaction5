package io.itaiit.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author itaiit
 * @date 2022/8/23 16:09
 */
@Data
public class Taco {
    private Long id;

    private Date createdAt;
    private String name;
    private List<Ingredient> ingredients;

}
