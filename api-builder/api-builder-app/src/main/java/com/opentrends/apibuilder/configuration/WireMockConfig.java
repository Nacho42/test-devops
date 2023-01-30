package com.opentrends.apibuilder.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
@ComponentScan("com.opentrends.apibuilder.configuration.*")
@Profile("mockserver")
public class WireMockConfig {

    @Value("${wiremock.port:9091}")
    private int port;

    @Bean
    @Profile("mockserver")
    public WireMockServer startWireMockServer(){
        final WireMockServer wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        return wireMockServer;
    }

}
