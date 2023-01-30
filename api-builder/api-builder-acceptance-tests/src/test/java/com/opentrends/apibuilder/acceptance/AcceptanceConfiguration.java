package com.opentrends.apibuilder.acceptance;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.contract.wiremock.WireMockConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import com.opentrends.apibuilder.ApiBuilderApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;

@ComponentScan(basePackages = {
        "com.opentrends.apibuilder.acceptance.steps",
        "com.opentrends.apibuilder.acceptance.worlds"
})
@Import({WireMockConfiguration.class})
public class AcceptanceConfiguration {

    private ConfigurableApplicationContext context;

    @Autowired
    WireMockConfiguration wireMockConfiguration;

    @Autowired
    WireMockServer wireMockServer;

    @PostConstruct
    public void runApplication(){
        wireMockConfiguration.start();

        context = new SpringApplicationBuilder(ApiBuilderApplication.class)
                .profiles("test")
                .run("--wiremock.server.port=" + wireMockServer.port(), "--server.port=0");
    }

    @PreDestroy
    public void stopApplication(){
        if (context != null){
            context.close();
        }
    }

    @Bean
    public WebTestClient webClient(){
        String localPort = context.getEnvironment().getProperty("local.server.port");
        String baseUrl = "http://localhost:" + localPort;
        String apiPrefix = context.getEnvironment().getProperty("server.servlet.context-path");

        return WebTestClient
                .bindToServer()
                .baseUrl(apiPrefix != null ? baseUrl + apiPrefix : baseUrl)
                .responseTimeout(Duration.ofMillis(300000))
                .build();
    }
}
