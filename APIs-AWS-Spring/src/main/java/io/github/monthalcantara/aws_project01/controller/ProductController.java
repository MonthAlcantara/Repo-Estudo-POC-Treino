package io.github.monthalcantara.aws_project01.controller;

import io.github.monthalcantara.aws_project01.model.Product;
import io.github.monthalcantara.aws_project01.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity findById(@PathVariable Long idProduto) {
        Optional<Product> product = Optional.empty();
        return ResponseEntity.ok(
                product.orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"))
        );
    }

    @PostMapping
    public ResponseEntity create(@PathVariable Product product) {
        Product productSave = productRepository.save(product);
        return new ResponseEntity(productSave, HttpStatus.CREATED);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity update(@PathVariable Long idProduct, @RequestBody Product product) {
        Optional<Product> productOptional = productRepository.findById(idProduct);
        if (productOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        product.setId(idProduct);
        Product productSave = productRepository.save(product);

        return ResponseEntity.ok(productSave);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity delete(@PathVariable Long idProduct) {
        Optional<Product> productOptional = productRepository.findById(idProduct);
        if (productOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        productRepository.deleteById(idProduct);
        return ResponseEntity.noContent().build();
    }

}
