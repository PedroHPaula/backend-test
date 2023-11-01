package php.backendtest.service.implementation;

import org.springframework.stereotype.Service;
import php.backendtest.entity.Installment;
import php.backendtest.entity.PaymentInfo;
import php.backendtest.repository.InstallmentRepository;
import php.backendtest.service.InstallmentService;
import php.backendtest.service.PaymentInfoService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final PaymentInfoService paymentInfoService;
    private final BigDecimal fixedMonthlyInterestRate = BigDecimal.valueOf(0.0115);

    public InstallmentServiceImpl(InstallmentRepository installmentRepository, PaymentInfoService paymentInfoService) {
        this.installmentRepository = installmentRepository;
        this.paymentInfoService = paymentInfoService;
    }

    @Override
    public Installment findById(Long id) {
        return installmentRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Installment> findAllByPaymentId(Long paymentId) {
        return installmentRepository.findAllByPaymentId(paymentId);
    }

    @Override
    public Installment save(Installment installment) {

        Long installmentId = installment.getId();

        if (installmentId != null && installmentRepository.existsById(installmentId)) {
            throw new IllegalArgumentException("O ID desta parcela já está cadastrado.");
        }

        PaymentInfo paymentInfo = installment.getPaymentInfo();

        installment.setPaymentInfo(
                paymentInfoService.findById(paymentInfo.getId())
        );

        BigDecimal numberOfInstallments = BigDecimal.valueOf(paymentInfo.getNumberOfInstallments());

        if (installment.getPaymentInfo().getNumberOfInstallments() > 6) {
            installment.setMonthlyInterestRate(fixedMonthlyInterestRate);
        } else {
            installment.setMonthlyInterestRate(BigDecimal.ZERO);
        }

        BigDecimal productPrice = paymentInfo.getProduct().getPrice();
        BigDecimal entry = paymentInfo.getEntry();
        BigDecimal totalPriceOfInstallments = productPrice.subtract(entry);
        BigDecimal priceOfInstallmentWithoutInterest = totalPriceOfInstallments
                                                        .divide(numberOfInstallments, RoundingMode.DOWN);

        installment.setPrice(
            priceOfInstallmentWithoutInterest.multiply(BigDecimal.ONE.add(fixedMonthlyInterestRate))
        );

        return installmentRepository.save(installment);
    }
}
