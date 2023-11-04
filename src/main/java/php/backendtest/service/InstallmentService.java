package php.backendtest.service;

import php.backendtest.entity.Installment;
import php.backendtest.entity.PaymentInfo;

import java.util.List;

public interface InstallmentService {

    Installment findById(Long id);

    List<Installment> findAllByPaymentId(Long paymentId);

    Installment save(Installment installment);

}
