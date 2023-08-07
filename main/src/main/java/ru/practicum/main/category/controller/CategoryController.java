package ru.practicum.main.category.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.service.CategoryService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getCategories(@RequestParam(defaultValue = "0") int from,
                                           @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {
        log.debug("GET request to /categories with parameters: from = " + from + " size = " + size);
        return categoryService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategory(@PathVariable Integer id) {
        log.debug("GET request to /categories/{id} with parameters: id = " + id);
        return categoryService.getCategory(id);
    }

}
