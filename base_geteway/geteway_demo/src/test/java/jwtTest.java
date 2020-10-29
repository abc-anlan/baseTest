import io.jsonwebtoken.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class jwtTest {
    public static void main(String[] args) {
        //获取系统当前时间
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);

        //生成jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("test")  //设置jwt编码
                .setSubject("测试")  //设置jwt主题,可以是json数据
                .claim("自定义数据",date)  //自定义数据
                .claim("当前时间",format)  //自定义数据
                .setIssuedAt(new Date())  //设置jwt签发日期
//                .setExpiration(date)  //设置jwt过期时间，生成同时过期
                .signWith(SignatureAlgorithm.HS256, "anlan");


        //生成jwt
        String token = jwtBuilder.compact();
        System.out.println(token);


        //解析jwt
        Claims claims = Jwts.parser().setSigningKey("anlan").parseClaimsJws(token).getBody();
        System.out.println(claims);


    }
}
