package io.itaiit.data;


import io.itaiit.domain.Taco;

import java.util.List;
import java.util.Optional;

public interface TacoRepository {

  Taco save(Taco design);

  List<Taco> findAll();

  Optional<Taco> findById(Long id);
}
