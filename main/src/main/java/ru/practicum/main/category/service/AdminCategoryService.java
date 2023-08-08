package ru.practicum.main.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.InputCategoryDto;


@RequiredArgsConstructor
@Service
public class AdminCategoryService {
    private final CategoryService categoryService;

    public CategoryDto postCategory(InputCategoryDto inputCategoryDto) {
        return categoryService.postCategory(inputCategoryDto);
    }

    public CategoryDto patchCategory(InputCategoryDto inputCategoryDto, Integer id) {
        return categoryService.patchCategory(inputCategoryDto, id);
    }

    public void deleteCategory(Integer id) {
        categoryService.deleteCategory(id);
    }
}
