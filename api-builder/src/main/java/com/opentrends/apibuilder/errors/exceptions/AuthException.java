package com.opentrends.apibuilder.errors.exceptions;

public class AuthException extends ApiBuilderException{
    private static final long serialVersionUID = 4109586617085060817L;

    private static final String DEFAULT_CODE = "apibuilder.not.credentials.error";
    private static final String DEFAULT_MESSAGE = "Error getting credentials";

    public AuthException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public AuthException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }

    public static AuthException of(){
        return new AuthException(DEFAULT_CODE, DEFAULT_MESSAGE);
    }
    public static AuthException of(final Throwable cause){
        return new AuthException(DEFAULT_CODE, DEFAULT_MESSAGE, cause);
    }
}
