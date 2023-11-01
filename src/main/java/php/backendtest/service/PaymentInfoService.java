package php.backendtest.service;

import php.backendtest.entity.PaymentInfo;

public interface PaymentInfoService {

    PaymentInfo findById(Long id);

    PaymentInfo save(PaymentInfo paymentInfo);

}
