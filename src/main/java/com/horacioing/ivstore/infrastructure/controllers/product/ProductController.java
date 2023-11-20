package com.horacioing.ivstore.infrastructure.controllers.product;

import com.horacioing.ivstore.application.services.ProductService;
import com.horacioing.ivstore.domain.models.product.Product;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
@Tag(name = "Product", description = "Product API")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(
            @RequestBody
            DataProductRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ){
        Product productSaved = this.productService.createProduct(
                Product.builder()
                        .name(dataRegister.name())
                        .description(dataRegister.description())
                        .color(dataRegister.color())
                        .category(dataRegister.category())
                        .size(dataRegister.size())
                        .reference(dataRegister.reference())
                        .price(dataRegister.price())
                        .build()
        );

        return ResponseEntity.created(
                uriBuilder.path("/api/v1/product/{id}")
                        .buildAndExpand(productSaved.getId())
                        .toUri()
        ).body(productSaved);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
        return ResponseEntity.ok(this.productService.getProducts(pageable));
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<Product> getProductByReference(
            @PathVariable(name = "reference")
            String reference
    ){
        Optional<Product> product = this.productService.getProductByReference(reference);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(
            @PathVariable(name = "productId")
            Long productId
    ){
        Optional<Product> product = this.productService.getProduct(productId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(
            @RequestBody
            DataProductUpdate dataUpdate
    ){
        Optional<Product> productUpdate = this.productService.updateProduct(
                Product.builder()
                        .id(dataUpdate.id())
                        .name(dataUpdate.name())
                        .color(dataUpdate.color())
                        .description(dataUpdate.description())
                        .category(dataUpdate.category())
                        .size(dataUpdate.size())
                        .price(dataUpdate.price())
                        .build()
        );
        return productUpdate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(
            @PathVariable(name = "productId")
            Long productId
    ){
        boolean idDeleted = this.productService.deleteProduct(productId);
        return idDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
