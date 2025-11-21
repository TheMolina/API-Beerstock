package one.digitalinnovation.beerstock.exception;

public class BeerAlreadyRegisteredException extends RuntimeException {
    public BeerAlreadyRegisteredException(String name) {
        super(String.format("Beer with name '%s' is already registered.", name));
    }
}
