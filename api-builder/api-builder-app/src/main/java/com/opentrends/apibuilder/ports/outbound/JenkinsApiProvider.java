package com.opentrends.apibuilder.ports.outbound;

import com.opentrends.apibuilder.errors.exceptions.AuthException;
import com.opentrends.apibuilder.errors.exceptions.BeanAlreadyExistsException;

public interface JenkinsApiProvider {

    void build() throws BeanAlreadyExistsException, AuthException;
}
