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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto postCategory(@RequestBody @Valid InputCategoryDto inputCategoryDto) {
        log.debug("POST request to admin/categories with parameters: inputCategoryDto = " + inputCategoryDto);
        return adminCategoryService.postCategory(inputCategoryDto);
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto patchCategory(@RequestBody @Valid InputCategoryDto inputCategoryDto,
                                     @PathVariable Integer catId) {
        log.debug("PATCH request to admin/categories with parameters: catId = " + catId
                + "inputCategoryDto = " + inputCategoryDto);
        return adminCategoryService.patchCategory(inputCategoryDto, catId);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer catId) {
        log.debug("DELETE request to admin/categories with parameters: id = " + catId);
        adminCategoryService.deleteCategory(catId);
    }
}
