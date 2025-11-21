package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BeerServiceTest {

    private BeerRepository repository;
    private BeerService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(BeerRepository.class);
        service = new BeerService(repository);
    }

    @Test
    void create_shouldThrowOnDuplicateName() {
        BeerDTO dto = new BeerDTO();
        dto.setName("IPA");
        dto.setBrand("BrewCo");
        dto.setMax(50);
        dto.setQuantity(10);
        dto.setPrice(BigDecimal.valueOf(8.5));

        Mockito.when(repository.findByNameIgnoreCase(ArgumentMatchers.eq("IPA"))).thenReturn(Optional.of(new Beer()));

        assertThatThrownBy(() -> service.create(dto)).isInstanceOf(BeerAlreadyRegisteredException.class);
    }
}
