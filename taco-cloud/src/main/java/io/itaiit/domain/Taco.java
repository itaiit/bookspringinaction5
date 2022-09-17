package io.itaiit.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author itaiit
 * @date 2022/8/23 16:09
 */
@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt = new Date();
    private String name;
    @ManyToMany // 默认的连接表为taco_ingredient
    @JoinTable(
            joinColumns = @JoinColumn(name = "taco", referencedColumnName = "id"), // owning side
            inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id")
    )
    private List<Ingredient> ingredients;

}
