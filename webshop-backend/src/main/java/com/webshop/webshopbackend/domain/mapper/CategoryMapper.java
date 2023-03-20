package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.CategoryDAO;
import com.webshop.webshopbackend.domain.DTO.CategoryDTO;
import com.webshop.webshopbackend.domain.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDTO>{
    private final CategoryDAO categoryDAO;

    public CategoryMapper(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Category fromDTOToEntity(CategoryDTO categoryDTO) {

        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );

        return category;
    }

    @Override
    public CategoryDTO fromEntityToDTO(Category category) {

        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    @Override
    public Category fromIdToEntity(String id) {

        if ( id == null ) {
            return null;
        }

        return categoryDAO.getById(id);
    }
}
