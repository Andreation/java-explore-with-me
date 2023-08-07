package ru.practicum.main.request.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.request.dto.RequestDtoEvent;
import ru.practicum.main.request.service.RequestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestController {

    RequestService requestService;

    @GetMapping
    public List<RequestDtoEvent> getRequests(@PathVariable Integer userId) {
        return requestService.getRequests(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDtoEvent createRequest(@PathVariable Integer userId, @RequestParam Integer eventId) {
        return requestService.addRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public RequestDtoEvent cancelRequest(@PathVariable Integer userId, @PathVariable Integer requestId) {
        return requestService.cancelRequest(userId, requestId);
    }
}
