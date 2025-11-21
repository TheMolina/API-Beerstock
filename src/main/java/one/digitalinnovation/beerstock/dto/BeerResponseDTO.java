package one.digitalinnovation.beerstock.dto;

import java.math.BigDecimal;

public class BeerResponseDTO {
    private Long id;
    private String name;
    private String brand;
    private Integer max;
    private Integer quantity;
    private BigDecimal price;
    private boolean active = true;

    public BeerResponseDTO() {}

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
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
