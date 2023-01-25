package com.opentrends.apibuilder.acceptance;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.SpringFactory;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = "pretty",
        glue = {"com.opentrends.apibuilder.acceptance.steps"},
        objectFactory = SpringFactory.class)
public class AcceptanceCriteriaTest {
}
