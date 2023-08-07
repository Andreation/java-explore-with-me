package ru.practicum.main.event.dto;

import lombok.experimental.FieldDefaults;
import lombok.*;
import ru.practicum.main.request.dto.RequestDtoEvent;

import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {
    List<RequestDtoEvent> confirmedRequests;
    List<RequestDtoEvent> rejectedRequests;
}
