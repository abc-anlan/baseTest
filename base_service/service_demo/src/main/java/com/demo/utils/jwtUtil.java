package com.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.security.provider.MD5;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

public class jwtUtil {
    //有效期
    public static final Long JWT_TTL=3600000L;//60*60*1000  一个小时

    //设置密钥明文
    public static final String JWT_KEY="NDU0NTY4amhmc3NkeHp6eGNxdzIlMjFAJTIxQCUyM2ZmNQ==";

    /**
     * 解析token
     * @param jsonWebToken
     * @return
     */
    public Claims parseToken(String jsonWebToken) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(JWT_KEY)).parseClaimsJws(jsonWebToken).getBody();
        return claims;
    }

    /**
     * 新建token
     * @param issuer
     * @return
     */
    public String createToken( String issuer, Map map) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // 添加Token签发时间
        builder.setIssuedAt(now);
        // 添加Token过期时间
        if (JWT_TTL >= 0) {
            long expMillis = nowMillis + JWT_TTL;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //设置自定义数据--可修改
        if (!"".equals(map.get("userName"))&&map.get("userName")!=null) {

            builder.claim("userName",map.get("userName"));
        }

        // 生成JWT
        return builder.compact();
    }

    /**
     * 刷新令牌
     *
     * @param claims
     * @return
     */
    public String refreshToken(Claims claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuer((String) claims.get("iss")).setAudience((String) claims.get("aud"))
                .signWith(signatureAlgorithm, signingKey);

        // 添加Token签发时间
        builder.setIssuedAt(now);
        // 添加Token过期时间
        if (JWT_TTL >= 0) {
            long expMillis = nowMillis + JWT_TTL;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        // 生成Token
        return builder.compact();
    }

//    public static String creatJwt(String id,String subject, Long ttl){
//
//        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
//
//        long currentTimeMillis = System.currentTimeMillis();
//
//        Date data = new Date(currentTimeMillis);
//
//        if(ttl==null){
//            ttl=jwtUtil.JWT_TTL;
//        }
//
//        long exTTL = currentTimeMillis + ttl; //过期时间
//
//        Date exDate = new Date(exTTL);
//
//        SecretKey secretKey=generaKey();
//
//        JwtBuilder builder = Jwts.builder()
//                .setId(id)
//                .setSubject(subject)
//                .setIssuer("anlan")  //签发者
//                .setIssuedAt(data)   //签发时间
//                .signWith(hs256, secretKey) //使用对称算法加密签名
//                .setExpiration(exDate); //过期时间
//        return builder.compact();
//
//    }

//    private static SecretKey generaKey() {
//        try {
//            return KeyGenerator.getInstance(JWT_KEY).generateKey();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
