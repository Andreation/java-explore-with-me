package ru.practicum.main.compilation.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.compilation.dto.CompilationDto;
import ru.practicum.main.compilation.dto.NewCompilationDto;
import ru.practicum.main.compilation.dto.UpdateCompilationRequest;
import ru.practicum.main.compilation.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminCompilationController {

    CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addCompilation(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        return compilationService.addCompilation(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCompilation(@PathVariable Integer compId) {
        compilationService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCompilation(
            @PathVariable Integer compId,
            @Valid @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return compilationService.updateCompilation(compId, updateCompilationRequest);
    }
}
