package ru.practicum.main.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputCategoryDto {
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;
}
