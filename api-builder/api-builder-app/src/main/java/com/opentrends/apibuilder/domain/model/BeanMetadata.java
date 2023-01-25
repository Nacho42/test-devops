package com.opentrends.apibuilder.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class BeanMetadata {
    private Integer externalId;
    private String name;
    private String repositoryPath;
    private String version;
}
