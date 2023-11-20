package com.horacioing.ivstore.infrastructure.controllers.store;

import com.horacioing.ivstore.application.services.StoreService;
import com.horacioing.ivstore.domain.models.store.Store;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/store")
@AllArgsConstructor
@Tag(name = "Store", description = "Store API")
@CrossOrigin(origins = "*")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<Store> create(
            @RequestBody
            DataStoreRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ) {
        Store store = this.storeService.createStore(
                Store.builder()
                        .name(dataRegister.name())
                        .color(dataRegister.color())
                        .phone(dataRegister.phone())
                        .address(dataRegister.address())
                        .build()
        );

        return ResponseEntity.created(
                uriBuilder.path("/api/store/{id}")
                        .buildAndExpand(store.getId())
                        .toUri()
        ).body(store);
    }

    @GetMapping
    public ResponseEntity<Page<Store>> getAllStores(Pageable pagination) {
        return ResponseEntity.ok(this.storeService.getStores(pagination));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStoreById(
            @PathVariable(name = "storeId")
            Long id
    ) {
        Optional<Store> storeFound = this.storeService.getStore(id);
        return storeFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Store> updateStore(
            @RequestBody
            DataStoreUpdate dataUpdate
    ) {
        Optional<Store> storeFound = this.storeService.updateStore(
                Store.builder()
                        .id(dataUpdate.id())
                        .name(dataUpdate.name())
                        .color(dataUpdate.color())
                        .phone(dataUpdate.phone())
                        .address(dataUpdate.address())
                        .build()
        );
        return storeFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStoreById(
            @PathVariable(name = "storeId")
            Long id
    ){
        boolean isDeleted = this.storeService.deleteStore(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
