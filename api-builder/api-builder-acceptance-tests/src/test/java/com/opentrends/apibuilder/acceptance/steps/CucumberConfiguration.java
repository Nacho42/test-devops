package com.opentrends.apibuilder.acceptance.steps;

import com.opentrends.apibuilder.acceptance.AcceptanceConfiguration;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = AcceptanceConfiguration.class)
public class CucumberConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(CucumberConfiguration.class);

    @Before
    public void setUp() {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }
}
