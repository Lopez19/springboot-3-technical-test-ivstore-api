package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.item.Item;
import com.horacioing.ivstore.domain.ports.in.iterm.CreateItemUseCase;
import com.horacioing.ivstore.domain.ports.in.iterm.RetrieveItemUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class ItemService implements CreateItemUseCase, RetrieveItemUseCase {

    private final CreateItemUseCase createItemUseCase;
    private final RetrieveItemUseCase retrieveItemUseCase;

    @Override
    public Item createItem(Item item) {
        return this.createItemUseCase.createItem(item);
    }

    @Override
    public Page<Item> getAllItems(Pageable pagination) {
        return this.retrieveItemUseCase.getAllItems(pagination);
    }

    @Override
    public Page<Item> getAllItemsBySaleId(Long saleId, Pageable pagination) {
        return this.retrieveItemUseCase.getAllItemsBySaleId(saleId, pagination);
    }
}
