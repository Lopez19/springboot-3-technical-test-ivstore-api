package com.horacioing.ivstore.application.usecases.item;

import com.horacioing.ivstore.domain.models.item.Item;
import com.horacioing.ivstore.domain.ports.in.iterm.RetrieveItemUseCase;
import com.horacioing.ivstore.domain.ports.out.ItemRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class RetrieveItemUseCaseImpl implements RetrieveItemUseCase {
    private final ItemRepositoryPort itemRepositoryPort;

    @Override
    public Page<Item> getAllItems(Pageable pagination) {
        return this.itemRepositoryPort.findAll(pagination);
    }

    @Override
    public Page<Item> getAllItemsBySaleId(Long saleId, Pageable pagination) {
        return this.itemRepositoryPort.findAllBySaleId(saleId, pagination);
    }
}
