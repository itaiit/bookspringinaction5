//tag::recents[]
package io.itaiit.web.api;

import io.itaiit.data.TacoRepository;
import io.itaiit.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController("restDesignTacoController")
@RequestMapping(path = "/design",                      // <1>
        produces = "application/json")
@CrossOrigin(origins = "*")        // <2>
public class DesignTacoController {
  private TacoRepository tacoRepo;

  @Autowired
  EntityLinks entityLinks;

  public DesignTacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  @GetMapping("/recent")
  public CollectionModel<EntityModel<Taco>> recentTacos() {                 //<3>
    List<Taco> all = tacoRepo.findAll();
    CollectionModel<EntityModel<Taco>> wrap = CollectionModel.wrap(all);
//    wrap.add(Link.of("http://localhost:8080/design/recent", "recents"));

//    wrap.add(
//            WebMvcLinkBuilder.linkTo(DesignTacoController.class)
//                    .slash("recent")
//                    .withRel("recents")
//    );
    wrap.add(
            WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(DesignTacoController.class).recentTacos()
            ).withRel("recents")
    );

    return wrap;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepo.save(taco);
  }
  //end::postTaco[]

  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    if (optTaco.isPresent()) {
      return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}

