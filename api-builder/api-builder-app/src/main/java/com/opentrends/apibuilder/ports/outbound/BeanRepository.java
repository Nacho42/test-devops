package com.opentrends.apibuilder.ports.outbound;

import com.opentrends.apibuilder.domain.model.BeanMetadata;
import com.opentrends.apibuilder.errors.exceptions.ApiBuilderException;

public interface BeanRepository {

    void save(BeanMetadata beanMetadata) throws ApiBuilderException;
}
