/**
 * @author FW
 * @date 2023/12/10 23:16
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.Util;

import cn.hutool.http.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {
    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.secret}")
    private String secret;

    public Object getPhoneNumber(String code) {

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
        return result;
    }

    private static final String SECRET_KEY = "fmark0930"; // 替换为你的密钥
    private static final long EXPIRATION_TIME = 259200000; // 过期时间，这里设置为三天的毫秒数

    public static String getToken(Map<String,Object> json) {

        JwtBuilder jwtBuilder = Jwts.builder();

        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);//计算出token的过期时间

        return jwtBuilder
                //.setSubject()  //token标题
                .setExpiration(expirationDate)  //设置过期时间
                .setIssuedAt(new Date())  //token的生成时间
                .setAudience("wxmini")
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .addClaims(json)
                .compact();
    }

    public static Claims verifyToken(String token){
        Claims claims =  Jwts.parser()
                .setSigningKey(SECRET_KEY)//设置签名密钥
                .parseClaimsJws(token)//解析声明Jws
                .getBody();

        System.out.println(claims);
        return claims;
    }

    public Object getPhoneNumber2(String code) {

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
        return result;
    }

    public Object getPhoneNumber3(String code) {

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
        return result;
    }
}
