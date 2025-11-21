package one.digitalinnovation.beerstock.dto;

import java.math.BigDecimal;

public class PriceUpdateDTO {
    private BigDecimal price;
    public PriceUpdateDTO() {}
    public PriceUpdateDTO(BigDecimal price) { this.price = price; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
