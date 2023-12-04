/**
 * @author FW
 * @date 2023/12/4 16:06
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.service;

import cn.hutool.http.HttpUtil;
import com.bbwweb.Result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Value("${wxmini.appid}")
    private String appid;
    @Value("${wxmini.secret}")
    private String secret;

    @Override
    public Result UserLogin(String code) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?"+"appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";

        String res = HttpUtil.get(url);
        System.out.println(res);
        String uuid = UUID.randomUUID().toString();

        return Result.success(res);
    }
}
