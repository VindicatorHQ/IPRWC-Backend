package com.webshop.webshopbackend.domain.DAO;

import com.webshop.webshopbackend.domain.entity.Category;
import com.webshop.webshopbackend.domain.repository.CategoryRepository;
import com.webshop.webshopbackend.exception.NotFound;

import java.util.List;

public class CategoryDAO implements DAO<Category>{
    private final CategoryRepository categoryRepository;

    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getById(String id) throws NotFound {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFound("Category", id));
    }

    @Override
    public Category saveToDatabase(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(String id, Category categoryRequest) throws NotFound {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new NotFound("Category", id));

        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(String id) throws NotFound {
        if(categoryRepository.existsById(id))
            this.categoryRepository.deleteById(id);
        else {
            throw new NotFound("Category", id);
        }
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }
}
