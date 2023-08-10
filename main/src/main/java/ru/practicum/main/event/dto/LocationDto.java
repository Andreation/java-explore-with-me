package ru.practicum.main.event.dto;

import lombok.experimental.FieldDefaults;
import lombok.*;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    Double lat;
    Double lon;
}
