package org.yyf.springcloud.commons.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.DigestUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jwt 对象加密工具 
 * 依赖commons.lang3,com.auth0.jwt
 * @author yyf
 * @date   2019年8月31日
 */
public class JwtObjectUtil {
	/**
	 * 私钥
	 */
	private static final String SECRET = DigestUtils.md5DigestAsHex("org.yyf.secret".getBytes());
	/**
	 * 签发者
	 */
	private static final String ISSUER = "yyf";
	/**
	 * 过期时间 单位分钟
	 */
	private final static int EXPIRES = 30;
	/**
	 * 载体
	 */
	private static final String PAYLOAD = "payload";

	/**
	 * 对象加密
	 * @param object 对象
	 * @return
	 */
	public static <T> String sign(T object) {
		JWTCreator.Builder builder = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			builder = JWT.create().withIssuer(ISSUER).withExpiresAt(DateUtils.addMinutes(new Date(), EXPIRES));
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(object);
			builder.withClaim(PAYLOAD, jsonString);
			return builder.sign(algorithm);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 对象解密
	 * @param token 	加密后的token字符串
	 * @param classT	解密类型
	 * @return
	 */

	public static <T> T unSign(String token, Class<T> classT) {
		Map<String, Claim> claims = new HashMap<>();
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			claims = jwt.getClaims();
			
			//判断签发者
			if(!claims.get(PublicClaims.ISSUER).asString().equals(ISSUER)) {
				return null;
			}

			if (claims.containsKey(PublicClaims.EXPIRES_AT) && claims.containsKey(PAYLOAD)) {
				Date expiresDate = jwt.getExpiresAt();
				long exp = expiresDate.getTime();
				long currentTimeMillis = System.currentTimeMillis();
				if (exp > currentTimeMillis) {
					String json = claims.get(PAYLOAD).asString();
					ObjectMapper objectMapper = new ObjectMapper();
					return objectMapper.readValue(json, classT);
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

//	public static void main(String[] args) {
//		User user = new User();
//		user.setUserName("yyf");
//		user.setPassword("123");
//
//		String token = JwtObjectUtil.sign(user);
//		System.out.println("加密：" + token);
//
//		
////		String au= "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjoie1widXNlck5hbWVcIjpcInl5ZlwiLFwicGFzc3dvcmRcIjpcIjEyM1wifSIsImlzcyI6Inl5ZiIsImV4cCI6MTU2NzE5NzI5MH0.9lTUZAI1-RcHkK4x7JnqThbm7MBtiQN8WBTvYGydgH0";
//		
//		User userEx = JwtObjectUtil.unSign(token, User.class);
//
//		System.out.println(userEx.toString());
//
//	}

}
