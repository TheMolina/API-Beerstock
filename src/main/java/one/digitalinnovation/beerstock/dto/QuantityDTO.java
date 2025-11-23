package one.digitalinnovation.beerstock.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class QuantityDTO {
    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be >= 1")
    private Integer quantity;
    public QuantityDTO() {}
    public QuantityDTO(Integer quantity) { this.quantity = quantity; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
