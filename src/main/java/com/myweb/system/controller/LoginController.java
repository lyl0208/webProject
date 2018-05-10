package com.myweb.system.controller;

import com.myweb.core.BaseController;
import com.myweb.core.ResultMap;
import com.myweb.core.exception.ParameterException;
import com.myweb.core.utils.AesUtils;
import com.myweb.core.utils.StringUtil;
import com.myweb.core.utils.TokenUtil;
import com.myweb.system.model.LoginArgs;
import com.myweb.system.model.Token;
import com.myweb.system.model.User;
import com.myweb.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 登录相关controller
 */
@RestController
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultMap login(LoginArgs loginArgs) throws Exception {
        if (!loginArgs.checkParam(loginArgs.getUsername(),loginArgs.getPassword())) {
            throw new ParameterException();
        }
        User user = userService.findUserByUsername(loginArgs.getUsername());
        if (user == null) {
            return ResultMap.error("用户名不存在");
        }
        String encryptPwd = AesUtils.MD5(loginArgs.getPassword());
        if (!user.getPassword().equals(encryptPwd)) {
            return ResultMap.error("密码错误");
        }
        Token token = new Token();
        token.setLoginTime(System.currentTimeMillis());
        token.setUserId(user.getUserId());
        token.setUsername(user.getUsername());
        String tokenStr = TokenUtil.createToken(token);
        if (StringUtil.isBlank(tokenStr)) {
            return ResultMap.error("token生成错误");
        }

        return ResultMap.ok("登录成功").put("user",user).put("token",tokenStr);
    }


}
