/**
 * @author FW
 * @date 2023/12/4 16:06
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.service;

import cn.hutool.http.HttpUtil;

import com.bbwweb.Result.Result;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
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
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将 JSON 字符串转换为 Map<String, Object> 对象
            Map<String,Object> resultMap = objectMapper.readValue(res, Map.class);

            System.out.println(resultMap);
            //String uuid = UUID.randomUUID().toString();
            return Result.success(resultMap);
            // 打印转换后的 Map 对象
        } catch (JsonProcessingException e) {
            // 处理 JSON 处理或映射过程中的异常
            e.printStackTrace();
        }

        return null;
    }
}
