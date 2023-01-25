package com.opentrends.apibuilder.acceptance.steps;

import com.opentrends.apibuilder.acceptance.worlds.BuilderWorld;
import com.opentrends.apibuilder.adapters.inbound.rest.dto.BuilderRequest;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

public class BuildSteps {

    private final WebTestClient webTestClient;
    public final BuilderWorld builderWorld;

    public BuildSteps(final WebTestClient webTestClient,
                      final BuilderWorld builderWorld) {
        this.webTestClient = webTestClient;
        this.builderWorld = builderWorld;
    }

    @DataTableType
    public BuilderRequest builderRequest(Map<String, String> trnasfer) {
        return BuilderRequest.builder()
                .externalId(Integer.parseInt(trnasfer.get("externalId")))
                .name(trnasfer.get("name"))
                .repositoryPath(trnasfer.get("repositoryPath"))
                .version(trnasfer.get("version"))
                .build();
    }

    @Given("a valid request")
    public void aValidRequest(BuilderRequest builderRequest) {
        builderWorld.setRequest(builderRequest);
    }

    @When("call build service")
    public void callBuildService() {
        builderWorld.setResponse(
                webTestClient.post()
                        .uri("/build")
                        .bodyValue(builderWorld.getRequest())
                        .exchange()
        );
    }

    @Then("result is OK")
    public void resultIsOK() {
        builderWorld.getResponse().expectStatus().isOk();
    }
}
