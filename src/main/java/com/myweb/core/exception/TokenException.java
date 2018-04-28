package com.myweb.core.exception;

/**
 */
public class TokenException extends IException{
    public TokenException(String message) {
        super(401,message);
    }

    public TokenException(){
        super(401,"token有误或已过期");
    }
}
