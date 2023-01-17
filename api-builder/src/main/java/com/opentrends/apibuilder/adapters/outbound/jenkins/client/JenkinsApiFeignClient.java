package com.opentrends.apibuilder.adapters.outbound.jenkins.client;

import com.opentrends.apibuilder.adapters.outbound.jenkins.dto.ApiTokenResponse;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "jenkins", url = "${com.opentrends.api-builder.providers.jenkins.url}")
public interface JenkinsApiFeignClient {

    @GetMapping(value = "/crumbIssuer/api/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    Response getCrumb(@RequestHeader("Authorization") final String auth);

    @PostMapping(value = "/user/admin/descriptorByName/jenkins.security.ApiTokenProperty/generateNewToken", consumes = MediaType.APPLICATION_JSON_VALUE)
    ApiTokenResponse getApiToken(@RequestHeader("Cookie") final String cookie,
                                 @RequestHeader("Jenkins-Crumb") final String crumb,
                                 @RequestHeader("Authorization") final String auth);

    @PostMapping(value = "/job/{job}/build", consumes = MediaType.APPLICATION_JSON_VALUE)
    void build(@RequestHeader("Cookie") final String cookie,
               @RequestHeader("Authorization") final String auth,
               @PathVariable final String job);
}
