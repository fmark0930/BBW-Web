/**
 * @author FW
 * @date 2023/12/4 16:06
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.service;

import cn.hutool.http.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbwweb.Result.Result;
import com.bbwweb.Util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
            Map<String,Object> resultMap = objectMapper.readValue(res, Map.class);  //将获取到的微信返回数据转换为map格式

            String token = JwtUtil.getToken(resultMap);   //申城token
           // System.out.println(token);
            resultMap.put("token",token);    //将token装入要返回的json
            //String uuid = UUID.randomUUID().toString();

            return Result.success(resultMap);
            // 打印转换后的 Map 对象
        } catch (JsonProcessingException e) {
            // 处理 JSON 处理或映射过程中的异常
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Result getPhoneNumber(String code) {

        String result = null;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";//获取token
            String replaceUrl = url.replace("{0}", appid).replace("{1}", secret);
            String res = HttpUtil.get(replaceUrl);
            JSONObject json_token = JSON.parseObject(res);//将返回数据转为json
            String access_token = json_token.getString("access_token");//拿到返回回来的token

            String urlTwo = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token={0}";
            String replaceUrlTwo = urlTwo.replace("{0}",access_token);
            HashMap<String, Object> requestParam = new HashMap<>();//创建一个空map
            // 手机号调用凭证
            requestParam.put("code", code);//将前端传来的code装入map
            String jsonStr = JSON.toJSONString(requestParam);
            HttpResponse response = HttpRequest.post(replaceUrlTwo)
                    .header(Header.CONTENT_ENCODING, "UTF-8")
                    // 发送json数据需要设置contentType
                    .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .body(jsonStr)
                    .execute();
            if (response.getStatus() == HttpStatus.HTTP_OK) {
                result = response.body();
            }
        } catch (HttpException e) {
            e.printStackTrace();
        }
        return Result.success(result);
    }
}
