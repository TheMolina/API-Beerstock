package one.digitalinnovation.beerstock.exception;

public class BeerStockExceededException extends RuntimeException {
    public BeerStockExceededException(Long id, int attempted, int max) {
        super(String.format("Cannot update beer %d: attempted quantity %d exceeds max %d.", id, attempted, max));
    }
}
