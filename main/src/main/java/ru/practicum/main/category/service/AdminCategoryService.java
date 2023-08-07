package ru.practicum.main.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.InputCategoryDto;
import ru.practicum.main.category.model.Category;
import ru.practicum.main.category.model.CategoryMapper;
import ru.practicum.main.category.repository.CategoryRepository;
import ru.practicum.main.exception.NotFoundException;


@RequiredArgsConstructor
@Service
public class AdminCategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDto postCategory(InputCategoryDto inputCategoryDto) {
        return CategoryMapper.categoryToDto(categoryRepository.save(CategoryMapper.categoryFromSaveDto(inputCategoryDto)));
    }

    public CategoryDto patchCategory(InputCategoryDto inputCategoryDto, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("category with id = %d, not found", id)));
        category.setName(inputCategoryDto.getName());
        return CategoryMapper.categoryToDto(categoryRepository.save(category));
    }

    public void deleteCategory(Integer id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("category with id = %d, not found", id))));
    }
}
