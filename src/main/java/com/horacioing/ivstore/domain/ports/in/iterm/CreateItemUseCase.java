package com.horacioing.ivstore.domain.ports.in.iterm;

import com.horacioing.ivstore.domain.models.item.Item;

public interface CreateItemUseCase {
    Item createItem(Item item);
}
