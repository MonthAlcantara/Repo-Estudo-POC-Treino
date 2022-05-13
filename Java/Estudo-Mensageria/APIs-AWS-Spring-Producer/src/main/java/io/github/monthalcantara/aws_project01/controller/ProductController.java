package io.github.monthalcantara.aws_project01.controller;

import io.github.monthalcantara.aws_project01.enums.EventType;
import io.github.monthalcantara.aws_project01.model.Product;
import io.github.monthalcantara.aws_project01.repository.ProductRepository;
import io.github.monthalcantara.aws_project01.service.ProductPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPublisher productPublisher;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity findById(@PathVariable Long idProduto) {

        logger.info("Requisição de busca de produto pelo id Recebida");

        Optional<Product> product = productRepository.findById(idProduto);

        logger.info("Busca realizada com sucesso");

        if (product.isEmpty()) {

        logger.error("A busca de produto pelo id não retornou nenhum resultado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("A busca pelo Id encontrou um produto e será retornado");

        return ResponseEntity.ok(product.get());
    }

    @GetMapping("/code")
    public ResponseEntity findByCode(@RequestParam String code) {

        logger.info("Requisição de busca de produto pelo code recebida");

        Optional<Product> productOptional = productRepository.findByCode(code);

        logger.info("Busca realizada com sucesso");

        if (productOptional.isEmpty()) {

            logger.error("A busca de produto pelo code não retornou nenhum resultado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("A busca pelo code encontrou um produto e será retornado");

        return ResponseEntity.ok(productOptional.get());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Product product) {

        logger.info("Requisição para criação de produto recebida");

        Product productSave = productRepository.save(product);

        logger.info("Produto salvo com sucesso");

        productPublisher.publishProductEvent(productSave, EventType.PRODUCT_CREATED, "UserCriacao");

        logger.info("Produto postado no SNS com sucesso");

        return new ResponseEntity(productSave, HttpStatus.CREATED);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity update(@PathVariable Long idProduct, @RequestBody Product product) {

        logger.info("Requisição para atualização de produto recebida");

        Optional<Product> productOptional = productRepository.findById(idProduct);

        if (productOptional.isEmpty()) {

            logger.error("A busca de produto pelo id não retornou nenhum resultado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("A busca pelo Id encontrou um produtoo");

        product.setId(idProduct);
        Product productSave = productRepository.save(product);

        logger.info("Produto atualizado com sucesso");

        productPublisher.publishProductEvent(productSave, EventType.PRODUCT_UPDATE, "UserAtualizacao");

        logger.info("Produto postado no SNS com sucesso");

        return ResponseEntity.ok(productSave);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity delete(@PathVariable Long idProduct) {

        logger.info("Requisição para deletar produto recebida");
        Optional<Product> productOptional = productRepository.findById(idProduct);

        if (productOptional.isEmpty()) {

            logger.error("A busca de produto pelo id não retornou nenhum resultado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("A busca pelo Id encontrou um produtoo");

        productRepository.deleteById(idProduct);

        logger.info("Produto deletado com sucesso");

        productPublisher.publishProductEvent(productOptional.get(), EventType.PRODUCT_DELETED, "UserDelecao");

        logger.info("Produto postado no SNS com sucesso");

        return ResponseEntity.noContent().build();
    }

}
