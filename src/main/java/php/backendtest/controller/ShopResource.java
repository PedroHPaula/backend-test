package php.backendtest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import php.backendtest.dto.BuyDto;
import php.backendtest.dto.InstallmentView;
import php.backendtest.entity.Installment;
import php.backendtest.entity.PaymentInfo;
import php.backendtest.entity.Product;
import php.backendtest.service.InstallmentService;
import php.backendtest.service.PaymentInfoService;
import php.backendtest.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopResource {

    private final ProductService productService;
    private final PaymentInfoService paymentInfoService;
    private final InstallmentService installmentService;


    public ShopResource(ProductService productService, PaymentInfoService paymentInfoService, InstallmentService installmentService) {
        this.productService = productService;
        this.paymentInfoService = paymentInfoService;
        this.installmentService = installmentService;
    }

    @PostMapping
    public ResponseEntity<List<InstallmentView>> createBuy(@RequestBody BuyDto buyDto) {

        Product product = buyDto.productDto.toEntity();
        PaymentInfo paymentInfo = buyDto.paymentInfoDto.toEntity();

        product = productService.save(product);
        paymentInfo.setProduct(product);
        paymentInfo = paymentInfoService.save(paymentInfo);

        for (int i = 0; i < paymentInfo.getNumberOfInstallments(); i++) {
            Installment installment = new Installment();
            installment.setPaymentInfo(paymentInfo);
            installment.setInstallmentOfNumber(i+1);
            installmentService.save(installment);
        }

        List<Installment> listOfInstallments = installmentService.findAllByPaymentId(paymentInfo.getId());
        List<InstallmentView> installmentViews = listOfInstallments.stream()
                                                    .map(InstallmentView::new)
                                                    .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(installmentViews);
    }

}
