package com.opentrends.apibuilder.errors.exceptions;

public class BeanAlreadyExistsException extends ApiBuilderException{
    private static final String DEFAULT_CODE = "apibuilder.bean.exists.error";
    private static final String DEFAULT_MESSAGE = "The bean already exists";

    private static final long serialVersionUID = -4040830069752906007L;

    public BeanAlreadyExistsException(final String errorCode, final String errorMessage) {
        super(errorCode, errorMessage);
    }

    public BeanAlreadyExistsException(final String errorCode, final String errorMessage, final Throwable cause) {
        super(errorCode, errorMessage, cause);
    }

    public static BeanAlreadyExistsException of(){
        return new BeanAlreadyExistsException(DEFAULT_CODE, DEFAULT_MESSAGE);
    }
    public static BeanAlreadyExistsException of(final Throwable cause){
        return new BeanAlreadyExistsException(DEFAULT_CODE, DEFAULT_MESSAGE, cause);
    }
}
