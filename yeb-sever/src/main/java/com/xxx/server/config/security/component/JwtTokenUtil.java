package com.xxx.server.config.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwtToken工具栏
 */
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("604800")
    private  Long expiration;

    /**
     * 根据用户信息生产token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token){
        String username;
        try{
            Claims claims=getClaimsFormToken(token);
            username=claims.getSubject();
        }catch (Exception e){
            username=null;
        }
        return username;
    }
    /**
     * 判断token是否失效
     */
    public boolean validateToken(String token ,UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return username.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }

    /**
     * 判断token是否可以刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }
    public String refreshToken(String token){
        Claims claims=getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /**
     * 判断token是否失效
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date exprieDate=getExpriedDateFromToken(token);
        return exprieDate.before(new Date());

    }

    /**
     * 从token中获取失效时间
     * @param token
     * @return
     */
    private Date getExpriedDateFromToken(String token) {
        Claims claims=getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token 中获取负载
     */
    private Claims getClaimsFormToken(String token){
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        }
        return claims;
    }
    /**
     * 根据负载生成jwt token
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date generateExpirationDate() {
        // 失效时间为：当前时间 + 配置的过期时间
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
