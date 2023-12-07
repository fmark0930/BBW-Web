/**
 * @author FW
 * @date 2023/12/4 18:51
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.Util;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.jdbc.Null;
import org.springframework.boot.autoconfigure.ssl.SslBundleProperties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
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


}
