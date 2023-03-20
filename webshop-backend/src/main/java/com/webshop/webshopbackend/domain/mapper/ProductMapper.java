package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.ProductDAO;
import com.webshop.webshopbackend.domain.DTO.ProductDTO;
import com.webshop.webshopbackend.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

    private final ProductDAO productDAO;

    public ProductMapper(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product fromDTOToEntity(ProductDTO productDTO) {

        if (productDTO == null) {
            return null;
        }

        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setStock(productDTO.getStock());
        product.setPrice(productDTO.getPrice());

        return product;
    }

    @Override
    public ProductDTO fromEntityToDTO(Product product) {

        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setStock(product.getStock());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }

    @Override
    public Product fromIdToEntity(String id) {

        if (id == null) {
            return null;
        }

        return productDAO.getById(id);
    }
}