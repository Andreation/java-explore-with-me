package ru.practicum.main.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.model.Category;
import ru.practicum.main.category.model.CategoryMapper;
import ru.practicum.main.category.repository.CategoryRepository;
import ru.practicum.main.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategories(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from, size))
                .getContent()
                .stream()
                .map(CategoryMapper::categoryToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("category with id = %d, not found", id)));
        return null;
    }

}
