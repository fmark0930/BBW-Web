/**
 * @author FW
 * @date 2023/12/8 22:06
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.Util;

import cn.hutool.http.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbwweb.Result.Result;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

public class getPhone {
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
}
