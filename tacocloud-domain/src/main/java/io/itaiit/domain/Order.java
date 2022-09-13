//tag::all[]
//tag::allButValidation[]
package io.itaiit.domain;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@Table("tacoorders")
public class Order {

    @PrimaryKey
    private UUID id = Uuids.timeBased();

    private Date placedAt = new Date();
    @Column("user")
    private UserUDT user;

    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

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

    public void addDesign(TacoUDT taco) {
        this.tacos.add(taco);
    }

}
//end::allButValidation[]
//end::all[]
