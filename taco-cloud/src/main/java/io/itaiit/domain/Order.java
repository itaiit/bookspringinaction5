//tag::all[]
//tag::allButValidation[]
package io.itaiit.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "Taco_Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt;

    @PrePersist
    private void prePersist() {
        this.placedAt = new Date();
    }

    @Transient
    private User user;

    private String username;

    public void setUser(User user) {
        this.user = user;
        this.username = user.getUsername();
    }

    @Transient
    private List<Taco> tacos = new ArrayList<>();

    //end::allButValidation[]
    //tag::allButValidation[]
    private String name;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String street;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String city;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String state;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String zip;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String ccNumber;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String ccExpiration;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String ccCVV;

    public void addDesign(Taco taco) {
        this.tacos.add(taco);
    }

}
//end::allButValidation[]
//end::all[]
