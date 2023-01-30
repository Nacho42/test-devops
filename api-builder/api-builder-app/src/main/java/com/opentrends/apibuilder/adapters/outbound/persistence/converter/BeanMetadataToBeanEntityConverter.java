package com.opentrends.apibuilder.adapters.outbound.persistence.converter;

import com.opentrends.apibuilder.adapters.outbound.persistence.dto.BeanEntity;
import com.opentrends.apibuilder.domain.model.BeanMetadata;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface BeanMetadataToBeanEntityConverter extends Converter<BeanMetadata, BeanEntity> {
    BeanEntity convert(final BeanMetadata beanMetadata);
}
