package ru.practicum.main.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputUserDto {
    @NotBlank
    @Size(max = 50)
    private String name;
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;
}
