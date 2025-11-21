package one.digitalinnovation.beerstock.builder;

import one.digitalinnovation.beerstock.dto.BeerDTO;

import java.math.BigDecimal;

public class BeerDTOBuilder {

    public static BeerDTO createDefault() {
        BeerDTO dto = new BeerDTO();
        dto.setName("IPA");
        dto.setBrand("BrewCo");
        dto.setMax(50);
        dto.setQuantity(10);
        dto.setPrice(BigDecimal.valueOf(8.5));
        return dto;
    }
}
