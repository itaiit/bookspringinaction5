package io.itaiit.web.api.resource;

import io.itaiit.domain.Taco;
import io.itaiit.web.api.DesignTacoController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

/**
 * @author itaiit
 * @date 2022/8/28 14:45
 */
public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    public TacoResource toModel(Taco entity) {
        TacoResource modelWithId = createModelWithId(entity.getId(), entity);
        return modelWithId;
    }

    @Override
    protected TacoResource instantiateModel(Taco entity) {
        return new TacoResource(entity);
    }
}
