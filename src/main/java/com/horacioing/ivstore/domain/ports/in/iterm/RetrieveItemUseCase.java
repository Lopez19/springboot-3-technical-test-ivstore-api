package com.horacioing.ivstore.domain.ports.in.iterm;

import com.horacioing.ivstore.domain.models.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RetrieveItemUseCase {
    Page<Item> getAllItems(Pageable pagination);
    Page<Item> getAllItemsBySaleId(Long saleId, Pageable pagination);
}
