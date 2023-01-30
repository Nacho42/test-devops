package com.opentrends.apibuilder.acceptance.worlds;

import com.opentrends.apibuilder.adapters.inbound.rest.dto.BuilderRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient;

@Component
@Scope("cucumber-glue")
@Getter
@Setter
public class BuilderWorld {

    private WebTestClient.ResponseSpec response;

    private BuilderRequest request;
}
