package com.opentrends.apibuilder.adapters.outbound.jenkins;

import com.opentrends.apibuilder.adapters.outbound.jenkins.client.JenkinsApiFeignClient;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = {JenkinsApiAdapter.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableFeignClients(clients = {JenkinsApiFeignClient.class})
@AutoConfigureWireMock(port = 0)
@Import({
        org.springframework.cloud.openfeign.FeignAutoConfiguration.class,
        org.springframework.cloud.openfeign.encoding.FeignAcceptGzipEncodingAutoConfiguration.class,
        org.springframework.cloud.openfeign.encoding.FeignContentGzipEncodingAutoConfiguration.class})
public class JenkinsApiAdapterTestIT {

    @Autowired
    private JenkinsApiAdapter adapter;

    @Test
    @SneakyThrows
    public void test(){
        adapter.build();
    }
}
