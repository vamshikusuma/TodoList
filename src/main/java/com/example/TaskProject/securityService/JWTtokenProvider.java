package com.example.TaskProject.securityService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JWTtokenProvider {
	private static final long JWT_EXPIRATION = 3600000; // 7 days

	public String generateToken(Authentication authentication) {
		String Email = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

		String token = Jwts.builder()
				.setSubject(Email)
				.setIssuedAt(currentDate)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512,"qWErTYuiOPasDFghJKlzXCVbnMqweRTYUIopASdfGHJKLzxcvBNmqWeRtyUiOpaSDFGHjKLZXcvBNMqwErTyUiOpAsDfGhJkLzXcVbNmQWeRTYUIopASDFGHJKLzxcvbnmQWERtyuioPASDFghjklzXCVbnm\r\n"
						)
				.compact();
		return token;
	}
	public String getEmailFromToken(String token){
		if (token != null) {
			try {
				Claims claims = Jwts.parser()
						.setSigningKey("qWErTYuiOPasDFghJKlzXCVbnMqweRTYUIopASdfGHJKLzxcvBNmqWeRtyUiOpaSDFGHjKLZXcvBNMqwErTyUiOpAsDfGhJkLzXcVbNmQWeRTYUIopASDFGHJKLzxcvbnmQWERtyuioPASDFghjklzXCVbnm\r\n"
								)
						.build()
						.parseClaimsJws(token)
						.getBody();

				return claims.getSubject();
			} catch (Exception e) {
				System.err.println("Error parsing token: " + e.getMessage());
				return null;
			}
		}
		return null;
	}
	public boolean validate(String token) {
		if (token != null) {
			try {
				Claims claims = Jwts.parser()
						.setSigningKey("qWErTYuiOPasDFghJKlzXCVbnMqweRTYUIopASdfGHJKLzxcvBNmqWeRtyUiOpaSDFGHjKLZXcvBNMqwErTyUiOpAsDfGhJkLzXcVbNmQWeRTYUIopASDFGHJKLzxcvbnmQWERtyuioPASDFghjklzXCVbnm\r\n"
								)
						.build()
						.parseClaimsJws(token)
						.getBody();

				return true;
			} catch (Exception e) {
				System.err.println("Error parsing token: " + e.getMessage());
				return false;
			}
		}
		return false;
	}
	

}
