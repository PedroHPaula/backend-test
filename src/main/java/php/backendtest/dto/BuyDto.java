package php.backendtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyDto {

    @JsonProperty("produto")
    public ProductDto productDto;

    @JsonProperty("condicaoPagamento")
    public PaymentInfoDto paymentInfoDto;

}
