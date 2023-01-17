package com.opentrends.apibuilder.errors.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public class ApiBuilderException extends Exception{
    private static final long serialVersionUID = 6984084055123969906L;

    private static final String DEFAULT_CODE = "apibuilder.internal.server.error";
    private static final String DEFAULT_MESSAGE = "Internal Server Error";

    @Getter
    private final String errorCode;
    @Getter
    private final String errorMessage;

    @JsonCreator
    public ApiBuilderException(final String errorCode, final String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public ApiBuilderException(final String errorCode, final String errorMessage, final Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ApiBuilderException of(){
        return new ApiBuilderException(DEFAULT_CODE, DEFAULT_MESSAGE);
    }
    public static ApiBuilderException of(final Throwable cause){
        return new ApiBuilderException(DEFAULT_CODE, DEFAULT_MESSAGE, cause);
    }
}
