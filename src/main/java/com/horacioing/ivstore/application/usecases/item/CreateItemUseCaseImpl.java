package com.horacioing.ivstore.application.usecases.item;

import com.horacioing.ivstore.domain.models.item.Item;
import com.horacioing.ivstore.domain.ports.in.iterm.CreateItemUseCase;
import com.horacioing.ivstore.domain.ports.out.ItemRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateItemUseCaseImpl implements CreateItemUseCase {
    private final ItemRepositoryPort itemRepositoryPort;

    @Override
    public Item createItem(Item item) {
        return this.itemRepositoryPort.save(item);
    }
}
