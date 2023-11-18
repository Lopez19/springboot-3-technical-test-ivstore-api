package com.horacioing.ivstore.application.usecases.product;

import com.horacioing.ivstore.domain.ports.in.product.DeleteProductUseCase;
import com.horacioing.ivstore.domain.ports.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public boolean deleteProduct(Long id) {
        return this.productRepositoryPort.deleteById(id);
    }
}
