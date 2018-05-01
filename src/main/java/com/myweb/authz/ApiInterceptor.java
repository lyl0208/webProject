package com.myweb.authz;

import com.myweb.core.exception.TokenException;
import com.myweb.core.utils.StringUtil;
import com.myweb.core.utils.TokenUtil;
import com.myweb.system.model.Token;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
public class ApiInterceptor implements HandlerInterceptor{

    private final static Logger logger = Logger.getLogger(ApiInterceptor.class);

    /**
     * token过期时间（三十分钟）
     */
    private final static Long EXPIRE_TIME = 1000L * 60 * 30;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String tokenStr = !StringUtil.isBlank(httpServletRequest.getParameter("token"))?httpServletRequest.getParameter("token"):httpServletRequest.getHeader("token");
        if (StringUtil.isBlank(tokenStr)) {
            logger.info("token为空");
            throw new TokenException();
        }
        //解密token
        Token token = TokenUtil.decryptToken(tokenStr);
        if (!token.checkToken()) {
            logger.info("token有误");
            throw new TokenException();
        }
        httpServletRequest.setAttribute("userId",token.getUserId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
