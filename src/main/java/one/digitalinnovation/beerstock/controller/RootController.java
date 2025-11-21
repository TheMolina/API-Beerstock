package one.digitalinnovation.beerstock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Void> root() {
        return ResponseEntity.status(302).location(URI.create("/api/v1/beers")).build();
    }
}
