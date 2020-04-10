package org.yyf.springcloud.commons.jwt;

import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JWT权限验证工具类 map加密方法 依赖 com.auth0 commons.lang3
 * 
 * @author yyf
 * @date 2019年8月31日
 */
public class JwtMapUtil {
	/**
	 * 私钥
	 */
	private static final String SECRET = "org.yyf.secret";
	/**
	 * 签发者
	 */
//	private static final String ISSUER = "yyf";
	/**
	 * 过期时间 单位小时
	 */
//	private final static int EXPIRES = 2;

	/**
	 * 生成token
	 *
	 * @param claims
	 * @return
	 */
	public static String createToken(Map<String, String> claims) {
		JWTCreator.Builder builder = null;
		String token = "";
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			builder = JWT.create();
//					.withExpiresAt(DateUtils.addHours(new Date(), EXPIRES));
			claims.forEach(builder::withClaim);
			token = builder.sign(algorithm);
		} catch (Exception e) {
			// TODO log
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 验证jwt，并返回数据
	 */
	public static Map<String, String> verifyToken(String token) {
		Map<String, Claim> map = new HashMap<>();
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			map = jwt.getClaims();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> resultMap = new HashMap<>(map.size());
		map.forEach((k, v) -> resultMap.put(k, v.asString()));
		return resultMap;
	}
	
	public static void main(String[] args) {
		Map<String,String> claims = new HashMap<>();
		claims.put("user", "test");
		claims.put("pass", "test123");
		String token = JwtMapUtil.createToken(claims);
		System.out.println("加密结果：" +token);
		
		Map<String, String> res = verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzIjoidGVzdDEyMyIsInVzZXIiOiJ0ZXN0In0.zn0rhbrf6dYGZXmMPfJBi-i8Gz5fuM7kjQNlTL2gA90");
	    System.out.println("解密Map:"+res.toString());
		
	}
	
}