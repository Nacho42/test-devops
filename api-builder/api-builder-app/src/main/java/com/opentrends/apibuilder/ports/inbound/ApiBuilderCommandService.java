package com.opentrends.apibuilder.ports.inbound;

import com.opentrends.apibuilder.domain.model.BeanMetadata;
import com.opentrends.apibuilder.errors.exceptions.ApiBuilderException;

public interface ApiBuilderCommandService {
    void build(BeanMetadata beanMetadata) throws ApiBuilderException;
}
