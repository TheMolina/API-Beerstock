package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.BeerResponseDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeerService {

    private final BeerRepository repository;

    public BeerService(BeerRepository repository) {
        this.repository = repository;
    }

    public BeerResponseDTO create(BeerDTO dto) {
        repository.findByNameIgnoreCase(dto.getName()).ifPresent(b -> {
            throw new one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException(dto.getName());
        });
        Beer beer = BeerMapper.toEntity(dto);
        if (beer.getQuantity() == null) beer.setQuantity(0);
        Beer saved = repository.save(beer);
        return BeerMapper.toResponseDTO(saved);
    }

    public List<BeerResponseDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(BeerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<BeerResponseDTO> findById(Long id) {
        return repository.findById(id).map(BeerMapper::toResponseDTO);
    }

    public BeerResponseDTO update(Long id, BeerDTO dto) {
        Beer beer = repository.findById(id).orElseThrow(() -> new one.digitalinnovation.beerstock.exception.BeerNotFoundException(id));
        repository.findByNameIgnoreCase(dto.getName()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) throw new one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException(dto.getName());
        });
        beer.setName(dto.getName());
        beer.setBrand(dto.getBrand());
        beer.setMax(dto.getMax());
        beer.setQuantity(dto.getQuantity());
        beer.setPrice(dto.getPrice());
        Beer saved = repository.save(beer);
        return BeerMapper.toResponseDTO(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new one.digitalinnovation.beerstock.exception.BeerNotFoundException(id);
        repository.deleteById(id);
    }

    public BeerResponseDTO updatePrice(Long id, BigDecimal price) {
        Beer beer = repository.findById(id).orElseThrow(() -> new one.digitalinnovation.beerstock.exception.BeerNotFoundException(id));
        beer.setPrice(price);
        return BeerMapper.toResponseDTO(repository.save(beer));
    }

    public Optional<BeerResponseDTO> increment(Long id, int amount) {
        return repository.findById(id).map(beer -> {
            int current = (beer.getQuantity() == null ? 0 : beer.getQuantity());
            int attempted = current + amount;
            Integer max = beer.getMax();
            if (max != null && attempted > max) {
                throw new one.digitalinnovation.beerstock.exception.BeerStockExceededException(id, attempted, max);
            }
            beer.setQuantity(attempted);
            return BeerMapper.toResponseDTO(repository.save(beer));
        });
    }

    public Optional<BeerResponseDTO> decrement(Long id, int amount) {
        return repository.findById(id).map(beer -> {
            int current = (beer.getQuantity() == null ? 0 : beer.getQuantity());
            int attempted = current - amount;
            if (attempted < 0) {
                throw new one.digitalinnovation.beerstock.exception.InsufficientStockException(id, amount, current);
            }
            beer.setQuantity(attempted);
            return BeerMapper.toResponseDTO(repository.save(beer));
        });
    }
}
