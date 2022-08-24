//tag::all[]
//tag::allButValidation[]
package io.itaiit.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class Order {

  private Long id;

  private Date placedAt;

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
