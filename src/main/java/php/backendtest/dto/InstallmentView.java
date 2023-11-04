package php.backendtest.dto;

import php.backendtest.entity.Installment;

import java.math.BigDecimal;

public class InstallmentView {

    private Integer numeroParcela;
    private BigDecimal valor;
    private BigDecimal taxaJurosAoMes;

    public InstallmentView(Installment installment) {
        this.numeroParcela = installment.getInstallmentOfNumber();
        this.valor = installment.getPrice();
        this.taxaJurosAoMes = installment.getMonthlyInterestRate();
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTaxaJurosAoMes() {
        return taxaJurosAoMes;
    }

    public void setTaxaJurosAoMes(BigDecimal taxaJurosAoMes) {
        this.taxaJurosAoMes = taxaJurosAoMes;
    }
}
