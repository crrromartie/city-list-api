package pl.solberg.cities.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.solberg.cities.dto.CityResponseDto;
import pl.solberg.cities.dto.CityUpdateDto;
import pl.solberg.cities.service.ICityService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CityController {

    private final ICityService service;

    @SecurityRequirement(name = "update-city")
    @Operation(description = "Update city")
    @PutMapping
    public CityResponseDto update(
            @Parameter(description = "Updated city", required = true)
            @Valid @RequestBody CityUpdateDto cityUpdateDto) {
        log.info("PUT /cities request:: {}", cityUpdateDto);

        var response = service.update(cityUpdateDto);

        log.info("PUT /cities response:: {}", response);
        return response;
    }

    @Operation(description = "Find city by name")
    @GetMapping
    public List<CityResponseDto> findByName(
            @Parameter(description = "Name of city", required = true)
            @RequestParam("name") String name) {
        log.info("GET /cities request:: name: {}", name);

        var response = service.findByName(name);

        log.info("GET /cities response:: {}", response);
        return response;
    }

    @Operation(description = "Find cities per page")
    @GetMapping("/all")
    public List<CityResponseDto> findPerPage(
            @Parameter(description = "Page number", required = true)
            @PositiveOrZero @RequestParam(value = "page", defaultValue = "1") int page,

            @Parameter(description = "Page size", required = true)
            @PositiveOrZero @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("GET /cities/all request:: page: {}, size: {}", page, size);

        var response = service.findPerPage(page, size);

        log.info("GET /cities/all response:: {}", response);
        return response;
    }
}
