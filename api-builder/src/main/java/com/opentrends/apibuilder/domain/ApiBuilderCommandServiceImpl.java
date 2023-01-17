package com.opentrends.apibuilder.domain;

import com.opentrends.apibuilder.domain.model.BeanMetadata;
import com.opentrends.apibuilder.errors.exceptions.ApiBuilderException;
import com.opentrends.apibuilder.ports.inbound.ApiBuilderCommandService;
import com.opentrends.apibuilder.ports.outbound.BeanRepository;
import com.opentrends.apibuilder.ports.outbound.JenkinsApiProvider;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ApiBuilderCommandServiceImpl implements ApiBuilderCommandService {

    private final BeanRepository beanRepository;
    private final JenkinsApiProvider jenkinsApiProvider;

    public ApiBuilderCommandServiceImpl(final BeanRepository beanRepository,
                                        final JenkinsApiProvider jenkinsApiProvider) {
        this.beanRepository = beanRepository;
        this.jenkinsApiProvider = jenkinsApiProvider;
    }

    @Override
    @Transactional
    public void build(final BeanMetadata beanMetadata) throws ApiBuilderException {
        beanRepository.save(beanMetadata);
        jenkinsApiProvider.build();
    }
}
