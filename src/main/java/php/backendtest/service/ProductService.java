package php.backendtest.service;

import php.backendtest.entity.Product;

public interface ProductService {

    Product findById(Long id);

    Product save(Product product);

}
