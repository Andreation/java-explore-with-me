package ru.practicum.main.category.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.InputCategoryDto;
import ru.practicum.main.category.service.AdminCategoryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto postCategory(@RequestBody @Valid InputCategoryDto inputCategoryDto) {
        log.debug("POST request to admin/categories with parameters: inputCategoryDto = " + inputCategoryDto);
        return adminCategoryService.postCategory(inputCategoryDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto patchCategory(@RequestBody @Valid InputCategoryDto inputCategoryDto,
                                     @PathVariable Integer id) {
        log.debug("PATCH request to admin/categories with parameters: id = " + id
                + "inputCategoryDto = " + inputCategoryDto);
        return adminCategoryService.patchCategory(inputCategoryDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        log.debug("DELETE request to admin/categories with parameters: id = " + id);
        adminCategoryService.deleteCategory(id);
    }
}
