package one.digitalinnovation.beerstock.controller;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.BeerResponseDTO;
import one.digitalinnovation.beerstock.dto.PriceUpdateDTO;
import one.digitalinnovation.beerstock.dto.QuantityDTO;
import one.digitalinnovation.beerstock.service.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private final BeerService service;

    public BeerController(BeerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BeerResponseDTO> create(@Valid @RequestBody BeerDTO dto) {
        BeerResponseDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/v1/beers/" + created.getId())).body(created);
    }

    @GetMapping
    public List<BeerResponseDTO> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerResponseDTO> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeerResponseDTO> update(@PathVariable Long id, @Valid @RequestBody BeerDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/increment")
    public ResponseEntity<BeerResponseDTO> increment(@PathVariable Long id, @Valid @RequestBody QuantityDTO qty) {
        return service.increment(id, qty.getQuantity()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/decrement")
    public ResponseEntity<BeerResponseDTO> decrement(@PathVariable Long id, @Valid @RequestBody QuantityDTO qty) {
        return service.decrement(id, qty.getQuantity()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<BeerResponseDTO> updatePrice(@PathVariable Long id, @Valid @RequestBody PriceUpdateDTO body) {
        return ResponseEntity.ok(service.updatePrice(id, body.getPrice()));
    }
}
