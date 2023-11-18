package com.horacioing.ivstore.infrastructure.controllers.category;

import com.horacioing.ivstore.application.services.CategoryService;
import com.horacioing.ivstore.domain.models.category.Category;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Category> create(
            @RequestBody
            DataCategoryRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ) {
        Category category = this.service.createCategory(
                Category.builder()
                        .name(dataRegister.name())
                        .description(dataRegister.description())
                        .color(dataRegister.color())
                        .build()
        );

        return ResponseEntity.created(
                uriBuilder.path("/api/categories/{id}")
                        .buildAndExpand(category.getId())
                        .toUri()
        ).body(category);
    }

    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pagination) {
        return ResponseEntity.ok(this.service.getCategories(pagination));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable(name = "categoryId")
            Long id
    ) {
        Optional<Category> category = this.service.getCategory(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(
            @RequestBody
            DataCategoryUpdate dataUpdate
    ) {
        Optional<Category> category = this.service.updateCategory(
                Category.builder()
                        .id(dataUpdate.id())
                        .name(dataUpdate.name())
                        .description(dataUpdate.description())
                        .color(dataUpdate.color())
                        .build()
        );
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(
            @PathVariable(name = "categoryId")
            Long id
    ) {
        boolean result = this.service.deleteCategory(id);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
