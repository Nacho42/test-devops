package com.opentrends.apibuilder.adapters.outbound.jenkins.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiTokenResponse {
    private String status;
    private ApiDataResponse data;
}
