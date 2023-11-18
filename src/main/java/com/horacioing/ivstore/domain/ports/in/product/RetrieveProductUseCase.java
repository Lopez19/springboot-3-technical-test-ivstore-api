package com.horacioing.ivstore.domain.ports.in.product;

import com.horacioing.ivstore.domain.models.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveProductUseCase {
    Optional<Product> getProduct(Long productId);
    Optional<Product> getProductByReference(String reference);
    Page<Product> getProducts(Pageable pageable);
}
