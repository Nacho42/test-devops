package com.opentrends.apibuilder.adapters.outbound.persistence;

import com.opentrends.apibuilder.adapters.outbound.persistence.dto.BeanEntity;
import com.opentrends.apibuilder.adapters.outbound.persistence.repositories.BeanEntityRepository;
import com.opentrends.apibuilder.domain.model.BeanMetadata;
import com.opentrends.apibuilder.errors.exceptions.ApiBuilderException;
import com.opentrends.apibuilder.errors.exceptions.BeanAlreadyExistsException;
import com.opentrends.apibuilder.ports.outbound.BeanRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DBBeanRepository implements BeanRepository {

    private final BeanEntityRepository repository;
    private final ConversionService conversionService;

    public DBBeanRepository(final BeanEntityRepository repository, final ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    @Override
    public void save(final BeanMetadata beanMetadata) throws ApiBuilderException {
        final var beanOptional = repository.findById(beanMetadata.getExternalId());
        if (beanOptional.isPresent()){
            throw BeanAlreadyExistsException.of();
        }
        final BeanEntity beanEntity = Optional.ofNullable(conversionService.convert(beanMetadata, BeanEntity.class))
                .orElseThrow(ApiBuilderException::of);
        repository.save(beanEntity);
    }
}
