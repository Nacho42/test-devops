package com.opentrends.apibuilder.adapters.inbound.rest.converter;

import com.opentrends.apibuilder.adapters.inbound.rest.dto.BuilderRequest;
import com.opentrends.apibuilder.domain.model.BeanMetadata;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface BuilderRequestToBeanMetadataConverter extends Converter<BuilderRequest, BeanMetadata> {

    BeanMetadata convert(final BuilderRequest builderRequest);
}
