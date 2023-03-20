package com.webshop.webshopbackend.controller;

import com.webshop.webshopbackend.domain.DAO.ProductDAO;
import com.webshop.webshopbackend.domain.DTO.ProductDTO;
import com.webshop.webshopbackend.domain.entity.Product;
import com.webshop.webshopbackend.domain.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductDAO productDAO;
    private final ProductMapper productMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Product productRequest = productMapper.fromDTOToEntity(productDTO);
        Product product = this.productDAO.saveToDatabase(productRequest);

        return productMapper.fromEntityToDTO(product);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts() {
        return productDAO.getAll().stream().map(productMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable String id) {
        Product product = this.productDAO.getById(id);

        return productMapper.fromEntityToDTO(product);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) throws ParseException {
        productDTO.setId(id);
        Product productRequest = productMapper.fromDTOToEntity(productDTO);
        Product product = productDAO.update(id, productRequest);

        return productMapper.fromEntityToDTO(product);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable String id) {
        this.productDAO.delete(id);

        return "Product deleted.";
    }

}
