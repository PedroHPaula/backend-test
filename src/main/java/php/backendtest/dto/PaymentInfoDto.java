package php.backendtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import php.backendtest.entity.PaymentInfo;

import java.math.BigDecimal;

public class PaymentInfoDto {

    @JsonProperty("valorEntrada")
    private BigDecimal entry;

    @JsonProperty("qtdeParcelas")
    private Integer numberOfInstallments;

    public PaymentInfo toEntity() {
        return new PaymentInfo(
            this.entry,
            this.numberOfInstallments
        );
    }

}
