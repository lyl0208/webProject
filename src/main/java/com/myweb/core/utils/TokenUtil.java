package com.myweb.core.utils;

import com.myweb.system.model.Token;
import org.apache.log4j.Logger;

/**
 */
public class TokenUtil {

    private final static Logger logger = Logger.getLogger(TokenUtil.class);

    private final static String TOKEN_KEY = "123456";


    public static String createToken(Token token) {
        try {
            return AesUtils.aesEncryptHexString(JSONUtil.toJsonString(token),TOKEN_KEY);
        } catch (Exception e) {
            logger.info("token加密失败");
            //加密失败
            e.printStackTrace();
            return null;
        }
    }

    public static Token decryptToken(String token) {
        if (StringUtil.isBlank(token)) {
            return null;
        }

        try {
            String tokenOk = AesUtils.aesDecryptHexString(token, TOKEN_KEY);
            Token tk = JSONUtil.parseObject(tokenOk,Token.class);
            return tk;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("token解密失败");
            return null;
        }
    }


}
