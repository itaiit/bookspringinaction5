//tag::recents[]
package io.itaiit.web.api;

import io.itaiit.data.TacoRepository;
import io.itaiit.domain.Taco;
import io.itaiit.web.prop.TacoPageProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController("restDesignTacoController")
@RequestMapping(path = "/design",                      // <1>
        produces = "application/json")
@CrossOrigin(origins = "*")        // <2>
public class DesignTacoController {
    private TacoRepository tacoRepo;

    @Autowired
    private TacoPageProp tacoPageProp;

    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

//    @GetMapping("/recent")
//    public List<Taco> recentTacos() {
//        Sort createdAtDesc = Sort.by("createdAt").descending();
//        PageRequest pageRequest = PageRequest.of(tacoPageProp.getPage(), tacoPageProp.getSize(), createdAtDesc);
//        return tacoRepo.findAll(pageRequest).getContent();
//    }

    @GetMapping("/recentreactive")
    public Flux<Taco> recentTacosReactive() {
        return tacoRepo.findAll().take(tacoPageProp.getSize());
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Taco taco) {

        return tacoRepo.save(taco);
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") UUID id) {
//        Optional<Taco> optTaco = tacoRepo.findById(id);
//        if (optTaco.isPresent()) {
//            return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return tacoRepo.findById(id);
    }
}

