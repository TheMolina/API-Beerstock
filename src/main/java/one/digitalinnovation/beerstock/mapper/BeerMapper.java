package one.digitalinnovation.beerstock.mapper;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.BeerResponseDTO;
import one.digitalinnovation.beerstock.entity.Beer;

public class BeerMapper {

    public static BeerDTO toDTO(Beer beer) {
        if (beer == null) return null;
        BeerDTO dto = new BeerDTO();
        dto.setId(beer.getId());
        dto.setName(beer.getName());
        dto.setBrand(beer.getBrand());
        dto.setMax(beer.getMax());
        dto.setQuantity(beer.getQuantity());
        dto.setPrice(beer.getPrice());
        return dto;
    }

    public static Beer toEntity(BeerDTO dto) {
        if (dto == null) return null;
        Beer b = new Beer();
        b.setId(dto.getId());
        b.setName(dto.getName());
        b.setBrand(dto.getBrand());
        b.setMax(dto.getMax());
        b.setQuantity(dto.getQuantity());
        b.setPrice(dto.getPrice());
        return b;
    }

    public static BeerResponseDTO toResponseDTO(Beer beer) {
        if (beer == null) return null;
        BeerResponseDTO r = new BeerResponseDTO();
        r.setId(beer.getId());
        r.setName(beer.getName());
        r.setBrand(beer.getBrand());
        r.setMax(beer.getMax());
        r.setQuantity(beer.getQuantity());
        r.setPrice(beer.getPrice());
        r.setActive(true);
        return r;
    }
}
