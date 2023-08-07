package ru.practicum.main.request.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.practicum.main.event.model.Event;
import ru.practicum.main.event.repository.EventRepository;
import ru.practicum.main.event.service.EventService;
import ru.practicum.main.exception.ConflictException;
import ru.practicum.main.exception.NotFoundException;
import ru.practicum.main.request.dto.RequestDtoEvent;
import ru.practicum.main.request.model.RequestEvent;
import ru.practicum.main.request.model.RequestMapper;
import ru.practicum.main.request.model.Status;
import ru.practicum.main.request.repository.RequestRepository;
import ru.practicum.main.user.model.User;
import ru.practicum.main.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.practicum.main.event.model.State.CANCELED;
import static ru.practicum.main.event.model.State.PENDING;
import static ru.practicum.main.request.model.Status.CONFIRMED;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestService {

    RequestRepository requestRepository;
    UserRepository userRepository;
    EventRepository eventRepository;
    EventService eventService;

    public List<RequestDtoEvent> getRequests(Integer userId) {
        if (!userRepository.existsById(userId)) throw new NotFoundException("Пользователь не найден");
        List<RequestEvent> requests = requestRepository.findAllByRequester_Id(userId);
        return eventService.toParticipationRequestDtos(requests);
    }

    public RequestDtoEvent addRequest(Integer userId, Integer eventId) {
        if (requestRepository.existsParticipationRequestByRequester_idAndEvent_Id(userId, eventId)) {
            throw new ConflictException("request");
        }
        Event event = eventRepository.findById(eventId).orElseThrow(NotFoundException::new);
        if (event.getConfirmedRequests() != null && event.getParticipantLimit() != 0
                && event.getConfirmedRequests().equals(event.getParticipantLimit())) {
            throw new ConflictException("");
        }
        if (event.getInitiator().getId().equals(userId) || event.getState() == PENDING || event.getState() == CANCELED) {
            throw new ConflictException("");
        }
        User requester = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        RequestEvent requestEvent = RequestEvent.builder()
                .created(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS)).requester(requester).event(event).build();
        if (event.getParticipantLimit() == 0 || !event.getRequestModeration()) {
            requestEvent.setStatus((CONFIRMED));
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
        } else requestEvent.setStatus((Status.PENDING));
        requestRepository.save(requestEvent);
        RequestDtoEvent requestDtoEvent = RequestMapper.toRequestEvent(requestEvent);
        requestDtoEvent.setRequester(requestEvent.getRequester().getId());
        requestDtoEvent.setEvent(requestEvent.getEvent().getId());
        return requestDtoEvent;
    }

    public RequestDtoEvent cancelRequest(Integer userId, Integer requestId) {
        RequestEvent requestEvent = requestRepository
                .findById(requestId).orElseThrow(() -> new NotFoundException("request not found"));
        if (!requestEvent.getRequester().getId().equals(userId)) {
            throw new NotFoundException("user not owner this request");
        }
        requestEvent.setStatus(Status.CANCELED);
        requestRepository.save(requestEvent);
        RequestDtoEvent requestDtoEvent = RequestMapper.toRequestEvent(requestEvent);
        requestDtoEvent.setRequester(requestEvent.getRequester().getId());
        requestDtoEvent.setEvent(requestEvent.getEvent().getId());
        return requestDtoEvent;
    }
}
