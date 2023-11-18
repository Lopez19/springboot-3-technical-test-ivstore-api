package com.horacioing.ivstore.infrastructure.controllers.item;


import com.horacioing.ivstore.application.services.ItemService;
import com.horacioing.ivstore.domain.models.item.Item;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/items")
@AllArgsConstructor
@Tag(name = "Items", description = "The Items API")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<Page<Item>> getAllItems(
            Pageable pagination
    ) {
        return ResponseEntity.ok(this.itemService.getAllItems(pagination));
    }
}
