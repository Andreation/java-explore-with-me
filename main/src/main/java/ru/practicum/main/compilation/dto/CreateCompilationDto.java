package ru.practicum.main.compilation.dto;

import lombok.experimental.FieldDefaults;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompilationDto {
    List<Integer> events = List.of();
    Boolean pinned = false;
    @NotBlank
    @Size(max = 50)
    String title;
}
