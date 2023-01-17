package com.opentrends.apibuilder.adapters.inbound.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class BuilderRequest implements Serializable {
    private static final long serialVersionUID = -8819282258597236376L;

    @NotNull(message = "The field has not been informed")
    private Integer externalId;
    @NotBlank(message = "The field has not been informed")
    @Pattern(regexp = "([^0-9]*)$", message = "The field cannot contain digits")
    private String name;
    @NotBlank(message = "The field has not been informed")
    private String repositoryPath;
    @NotBlank(message = "The field has not been informed")
    @Pattern(regexp = "^([0-9]\\.[0-9]\\.[0-9])$", message = "The format of the field is M.m.f")
    private String version;
}
