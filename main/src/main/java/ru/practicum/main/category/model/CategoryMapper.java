package ru.practicum.main.category.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.InputCategoryDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CategoryMapper {

    public static CategoryDto categoryToDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public static Category categoryFromSaveDto(InputCategoryDto newCategoryDto) {
        return new Category(null, newCategoryDto.getName());
    }
}