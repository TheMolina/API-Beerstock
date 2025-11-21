package one.digitalinnovation.beerstock.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class BeerDTO {
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "brand is required")
    private String brand;

    @Min(value = 0, message = "max must be >= 0")
    private Integer max;

    @Min(value = 0, message = "quantity must be >= 0")
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = true, message = "price must be >= 0")
    private BigDecimal price;

    public BeerDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Integer getMax() { return max; }
    public void setMax(Integer max) { this.max = max; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
