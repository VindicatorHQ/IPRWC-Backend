package com.webshop.webshopbackend.domain.DAO;

import com.webshop.webshopbackend.domain.entity.Product;
import com.webshop.webshopbackend.domain.repository.ProductRepository;
import com.webshop.webshopbackend.exception.NotFound;

import java.util.List;

public class ProductDAO implements DAO<Product>{
    private final ProductRepository productRepository;

    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(String id) throws NotFound {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFound("Product", id));
    }

    @Override
    public Product saveToDatabase(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(String id, Product productRequest) throws NotFound {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFound("Product", id));

        product.setName(productRequest.getName());
        product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());
        product.setOrder(productRequest.getOrder());
        product.setCategory(productRequest.getCategory());

        return productRepository.save(product);
    }

    @Override
    public void delete(String id) throws NotFound {
        if(productRepository.existsById(id))
            this.productRepository.deleteById(id);
        else {
            throw new NotFound("Product", id);
        }
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

}
