package php.backendtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import php.backendtest.entity.Product;

import java.math.BigDecimal;


public class ProductDto {

    @JsonProperty("codigo")
    private Integer code;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("valor")
    private BigDecimal price;

    public Product toEntity() {
        return new Product(
            this.code,
            this.name,
            this.price
        );
    }

}
