package one.digitalinnovation.beerstock.exception;

public class BeerNotFoundException extends RuntimeException {
    public BeerNotFoundException(Long id) {
        super(String.format("Beer with id %d not found.", id));
    }
}
