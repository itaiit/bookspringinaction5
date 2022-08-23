//tag::all[]
//tag::allButValidation[]
package io.itaiit.domain;

import lombok.Data;

@Data
public class Order {

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

}
//end::allButValidation[]
//end::all[]
