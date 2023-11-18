package com.horacioing.ivstore.application.usecases.product;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.ports.in.product.RetrieveProductUseCase;
import com.horacioing.ivstore.domain.ports.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveProductUseCaseImpl implements RetrieveProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Optional<Product> getProduct(Long productId) {
        return this.productRepositoryPort.findById(productId);
    }

    @Override
    public Optional<Product> getProductByReference(String reference) {
        return this.productRepositoryPort.findByReference(reference);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return this.productRepositoryPort.findAll(pageable);
    }
}
