package com.webshop.webshopbackend.controller;

import com.webshop.webshopbackend.domain.DAO.CategoryDAO;
import com.webshop.webshopbackend.domain.DTO.CategoryDTO;
import com.webshop.webshopbackend.domain.entity.Category;
import com.webshop.webshopbackend.domain.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryDAO categoryDAO;
    private final CategoryMapper categoryMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        Category categoryRequest = categoryMapper.fromDTOToEntity(categoryDTO);
        Category category = this.categoryDAO.saveToDatabase(categoryRequest);

        return categoryMapper.fromEntityToDTO(category);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategories(){
        return categoryDAO.getAll().stream().map(categoryMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable String id){
        Category category = this.categoryDAO.getById(id);

        return categoryMapper.fromEntityToDTO(category);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@PathVariable String id, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        Category categoryRequest = categoryMapper.fromDTOToEntity(categoryDTO);
        Category category = categoryDAO.update(id, categoryRequest);

        return categoryMapper.fromEntityToDTO(category);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteCategory(@PathVariable String id) {
        this.categoryDAO.delete(id);

        return "Category deleted.";
    }
}
