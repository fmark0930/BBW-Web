/**
 * @author FW
 * @date 2023/12/4 18:51
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.Util;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.ssl.SslBundleProperties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "fmark0930"; // 替换为你的密钥
    private static final long EXPIRATION_TIME = 259200000; // 过期时间，这里设置为三天的毫秒数

    public static String generateToken(String jsonPayload) {

        JwtBuilder jwtBuilder = Jwts.builder();

        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);//计算出token的过期时间

        return jwtBuilder
                .setSubject(jsonPayload)  //要存储得到json数据
                .setExpiration(expirationDate)  //设置过期时间
                .setIssuedAt(new Date())  //token的生成时间
                .setIssuer("your_issuer")//发行者
                .setAudience("wxmini")
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public static void main(String[] args) {
        // 构造 JSON 字符串
        String jsonPayload = "{\n" +
                "  \"sub\": \"openid123456\",\n" +
                "  \"exp\": 1640995200,\n" +
                "  \"iat\": 1640991600,\n" +
                "  \"iss\": \"your_issuer\",\n" +
                "  \"client_id\": \"your_client_id\",\n" +
                "  \"custom_data\": {\n" +
                "    \"user_role\": \"admin\",\n" +
                "    \"user_permissions\": [\"read\", \"write\"]\n" +
                "  }\n" +
                "}";

        // 生成 JWT
        String jwt = generateToken(jsonPayload);
        System.out.println("Generated JWT: " + jwt);
    }
}
