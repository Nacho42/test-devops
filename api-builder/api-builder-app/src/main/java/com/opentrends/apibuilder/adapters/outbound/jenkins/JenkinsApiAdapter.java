package com.opentrends.apibuilder.adapters.outbound.jenkins;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentrends.apibuilder.adapters.outbound.jenkins.client.JenkinsApiFeignClient;
import com.opentrends.apibuilder.adapters.outbound.jenkins.dto.CrumbResponse;
import com.opentrends.apibuilder.errors.exceptions.AuthException;
import com.opentrends.apibuilder.ports.outbound.JenkinsApiProvider;
import feign.Response;
import io.micrometer.core.instrument.util.IOUtils;
import kotlin.text.Charsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JenkinsApiAdapter implements JenkinsApiProvider {

    private final String user;
    private final String pass;
    private final JenkinsApiFeignClient client;
    private final String jobName;

    public JenkinsApiAdapter(final JenkinsApiFeignClient client,
                             @Value("${com.opentrends.api-builder.providers.jenkins.user}") final String user,
                             @Value("${com.opentrends.api-builder.providers.jenkins.pass}")final String pass,
                             @Value("${com.opentrends.api-builder.providers.jenkins.job}") final String jobName) {
        this.client = client;
        this.user = user;
        this.pass = pass;
        this.jobName = jobName;
    }

    @Override
    public void build() throws AuthException {
        final var basicAuth = generateBasicAuth(user, pass);

        final var response = client.getCrumb(basicAuth);
        final var crumb = getCrumbFromResponse(response);
        final var cookie = getCookieFromResponse(response.headers().get("set-cookie"));

        final var apiToken = Optional.ofNullable(client.getApiToken(cookie, crumb, basicAuth)).orElseThrow(AuthException::of).getData().getTokenValue();
        final var basicAuthToken = generateBasicAuth(user, apiToken);
        client.build(cookie, basicAuthToken, jobName);
    }

    private String getCrumbFromResponse(final Response response) throws AuthException {
        final var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            final var crumbResponse = mapper.readValue(IOUtils.toString(response.body().asInputStream(), Charsets.UTF_8), CrumbResponse.class);
            return crumbResponse.getCrumb();
        }catch (IOException e) {
            throw AuthException.of(e);
        }
    }

    private String getCookieFromResponse(final Collection<String> cookies) {
        return cookies.stream().map(cookie ->
                Arrays.stream(cookie.split(";"))
                        .filter(it -> it.contains("JSESSIONID"))
                        .collect(Collectors.joining(";")))
                .collect(Collectors.joining(";"));
    }

    private String generateBasicAuth(final String user, final String pass){
        return "Basic " + new String(Base64Utils.encode((user + ":" + pass).getBytes()));
    }
}
