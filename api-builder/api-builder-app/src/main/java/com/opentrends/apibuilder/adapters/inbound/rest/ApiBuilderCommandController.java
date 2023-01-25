package com.opentrends.apibuilder.adapters.inbound.rest;

import com.opentrends.apibuilder.adapters.inbound.rest.dto.BuilderRequest;
import com.opentrends.apibuilder.domain.model.BeanMetadata;
import com.opentrends.apibuilder.errors.exceptions.ApiBuilderException;
import com.opentrends.apibuilder.ports.inbound.ApiBuilderCommandService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/build")
public class ApiBuilderCommandController {

    private final ApiBuilderCommandService service;
    private final ConversionService conversionService;

    public ApiBuilderCommandController(final ApiBuilderCommandService service,
                                       final ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @PostMapping
    public ResponseEntity<Void> build(@RequestBody @Valid final BuilderRequest request) throws ApiBuilderException {
        service.build(conversionService.convert(request, BeanMetadata.class));
        return ResponseEntity.ok().build();
    }
}
