package php.backendtest.service.implementation;

import org.springframework.stereotype.Service;
import php.backendtest.entity.PaymentInfo;
import php.backendtest.entity.Product;
import php.backendtest.repository.PaymentInfoRepository;
import php.backendtest.repository.ProductRepository;
import php.backendtest.service.PaymentInfoService;
import php.backendtest.service.ProductService;

import java.util.NoSuchElementException;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final PaymentInfoRepository paymentInfoRepository;
    private final ProductService productService;

    public PaymentInfoServiceImpl(PaymentInfoRepository paymentInfoRepository, ProductRepository productRepository, ProductService productService) {
        this.paymentInfoRepository = paymentInfoRepository;
        this.productService = productService;
    }

    @Override
    public PaymentInfo findById(Long id) {
        return paymentInfoRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public PaymentInfo save(PaymentInfo paymentInfo) {
        Long paymentInfoId = paymentInfo.getId();
        Product paymentInfoProduct = paymentInfo.getProduct();
        if (paymentInfoId != null && paymentInfoRepository.existsById(paymentInfoId)) {
            throw new IllegalArgumentException("O ID desta compra já está cadastrado.");
        }

        paymentInfo.setProduct(
                productService.findById(paymentInfoProduct.getId())
        );

        return paymentInfoRepository.save(paymentInfo);
    }
}
