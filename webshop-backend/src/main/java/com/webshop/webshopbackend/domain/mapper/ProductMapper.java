package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.ProductDAO;
import com.webshop.webshopbackend.domain.DTO.ProductDTO;
import com.webshop.webshopbackend.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

    private final ProductDAO productDAO;
    private final CategoryMapper categoryMapper;

    public ProductMapper(ProductDAO productDAO, CategoryMapper categoryMapper) {
        this.productDAO = productDAO;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Product fromDTOToEntity(ProductDTO productDTO) {

        if (productDTO == null) {
            return null;
        }

        Product product = new Product();

        product.setCategory(categoryMapper.fromIdToEntity(productDTO.getCategoryId()));
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setImageName(productDTO.getImageName());
        product.setDescription(productDTO.getDescription());
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

        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImageName(product.getImageName());
        productDTO.setDescription(product.getDescription());
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
