package com.opentrends.apibuilder.adapters.inbound.rest;


import com.opentrends.apibuilder.adapters.inbound.rest.dto.BuilderRequest;
import com.opentrends.apibuilder.domain.model.BeanMetadata;
import com.opentrends.apibuilder.ports.inbound.ApiBuilderCommandService;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApiBuilderCommandControllerTest {

    private ApiBuilderCommandService service;
    private ConversionService conversionService;
    private ApiBuilderCommandController controller;

    @BeforeEach
    public void setUp(){
        service = mock(ApiBuilderCommandService.class);
        conversionService = mock(ConversionService.class);
        controller = new ApiBuilderCommandController(service, conversionService);
    }

    @Test
    @SneakyThrows
    public void buildShouldReturnOK(){
        final var builderRequest = BuilderRequest.builder().build();
        final var bean = BeanMetadata.builder().build();
        final var expected = ResponseEntity.ok().build();

        when(conversionService.convert(builderRequest, BeanMetadata.class)).thenReturn(bean);
        doNothing().when(service).build(bean);

        final var result = controller.build(builderRequest);

        assertEquals(expected, result);
    }
}
