package php.backendtest.service.implementation;

import org.springframework.stereotype.Service;
import php.backendtest.entity.Product;
import php.backendtest.repository.ProductRepository;
import php.backendtest.service.ProductService;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Product save(Product product) {
        Long productId = product.getId();
        Integer productCode = product.getCode();
        if (productId != null && productRepository.existsById(productId)) {
            throw new IllegalArgumentException("O ID deste produto já está cadastrado.");
        } else if (productRepository.existsByCode(productCode)) {
            throw new IllegalArgumentException("O código deste produto já está cadastrado.");
        }

        return productRepository.save(product);
    }
}
