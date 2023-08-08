package ru.practicum.main.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.InputCategoryDto;
import ru.practicum.main.category.model.Category;
import ru.practicum.main.category.model.CategoryMapper;
import ru.practicum.main.category.repository.CategoryRepository;
import ru.practicum.main.event.service.EventService;
import ru.practicum.main.exception.ConflictException;
import ru.practicum.main.exception.NotFoundException;


@RequiredArgsConstructor
@Service
public class AdminCategoryService {
    private final CategoryRepository categoryRepository;
    private final EventService eventService;

    public CategoryDto postCategory(InputCategoryDto inputCategoryDto) {
        return CategoryMapper.categoryToDto(categoryRepository.save(CategoryMapper.categoryFromInputDto(inputCategoryDto)));
    }

    public CategoryDto patchCategory(InputCategoryDto inputCategoryDto, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(" not found category with id = " + id)));
        category.setName(inputCategoryDto.getName());
        return CategoryMapper.categoryToDto(categoryRepository.save(category));
    }

    public void deleteCategory(Integer id) {
        checkEvent(id);
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("not found category with id = " + id))));
    }

    private void checkEvent(Integer catId) {
        if (eventService.findFirstByCategoryId(catId) != null) {
            throw new ConflictException("category include event");
        }
    }
}
