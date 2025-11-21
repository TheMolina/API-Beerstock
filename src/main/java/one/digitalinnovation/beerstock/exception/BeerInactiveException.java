package one.digitalinnovation.beerstock.exception;

public class BeerInactiveException extends RuntimeException {
    public BeerInactiveException(Long id) {
        super(String.format("Beer %d is inactive.", id));
    }
}
