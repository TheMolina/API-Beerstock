package one.digitalinnovation.beerstock.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long id, int requested, int available) {
        super(String.format("Insufficient stock for beer %d: requested %d but available %d.", id, requested, available));
    }
}
