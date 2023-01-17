package com.opentrends.apibuilder.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.opentrends.apibuilder.adapters.outbound")
public class FeignConfiguration {

}
