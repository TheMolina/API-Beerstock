package one.digitalinnovation.beerstock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Beer Controller", description = "Endpoints para gerenciamento de cervejas")
public class BeerControllerDocs {

    @Operation(summary = "Controller de cervejas", description = "Documentação de endpoints relacionados a cervejas")
    public void docs() {}
}
