package ru.practicum.main.request.dto;

import lombok.experimental.FieldDefaults;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @Size(min = 6, max = 254)
    @Email
    String email;
    @NotBlank
    @Size(min = 2, max = 250)
    String name;
}
