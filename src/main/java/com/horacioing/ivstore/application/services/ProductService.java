package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.ports.in.product.CreateProductUseCase;
import com.horacioing.ivstore.domain.ports.in.product.DeleteProductUseCase;
import com.horacioing.ivstore.domain.ports.in.product.RetrieveProductUseCase;
import com.horacioing.ivstore.domain.ports.in.product.UpdateProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class ProductService implements CreateProductUseCase, DeleteProductUseCase, UpdateProductUseCase, RetrieveProductUseCase {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final RetrieveProductUseCase retrieveProductUseCase;

    @Override
    public Product createProduct(Product product) {
        return this.createProductUseCase.createProduct(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return this.deleteProductUseCase.deleteProduct(id);
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return this.retrieveProductUseCase.getProduct(productId);
    }

    @Override
    public Optional<Product> getProductByReference(String reference) {
        return this.retrieveProductUseCase.getProductByReference(reference);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return this.retrieveProductUseCase.getProducts(pageable);
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        return this.updateProductUseCase.updateProduct(product);
    }
}
