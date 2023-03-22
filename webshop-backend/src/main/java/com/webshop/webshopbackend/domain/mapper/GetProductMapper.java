package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.ProductDAO;
import com.webshop.webshopbackend.domain.DTO.GetProductDTO;
import com.webshop.webshopbackend.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProductMapper implements Mapper<Product, GetProductDTO> {
    private final ProductDAO productDAO;

    @Override
    public Product fromDTOToEntity(GetProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImageName(productDTO.getImageName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());

        return product;
    }

    @Override
    public GetProductDTO fromEntityToDTO(Product product) {
        if (product == null) {
            return null;
        }

        GetProductDTO productDTO = new GetProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setImageName(product.getImageName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory());
        productDTO.setCategoryName(product.getCategory().getName());

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
