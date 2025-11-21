package one.digitalinnovation.beerstock.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void getAll_shouldReturn200() {
        ResponseEntity<String> res = rest.getForEntity("/api/v1/beers", String.class);
        assertThat(res.getStatusCodeValue()).isEqualTo(200);
        assertThat(res.getBody()).isNotNull();
    }

    @Test
    void createAndDeleteFlow() {
        String json = "{\"name\":\"TestBeer\",\"brand\":\"TB\",\"max\":10,\"quantity\":1,\"price\":3.5}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<String> create = rest.postForEntity("/api/v1/beers", entity, String.class);
        assertThat(create.getStatusCodeValue()).isEqualTo(201);
        String location = create.getHeaders().getLocation().toString();
        ResponseEntity<String> get = rest.getForEntity(location, String.class);
        assertThat(get.getStatusCodeValue()).isEqualTo(200);

        rest.delete(location);
        ResponseEntity<String> afterDelete = rest.getForEntity(location, String.class);
        assertThat(afterDelete.getStatusCodeValue()).isEqualTo(404);
    }
}
