package com.medicion.contador.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.medicion.contador.security.entity.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	@SuppressWarnings("deprecation")
	public String generateToken(Authentication authentication) {
		try {
			UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
			String token = Jwts.builder().setSubject(usuarioPrincipal.getUsername())
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + expiration * 1000))
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
			return token;
		} catch (Exception e) {
			System.out.print("el problema es: "+ e.getMessage());
			return "";
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	@SuppressWarnings("deprecation")
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token mal formado" + e.getMessage());
		}catch (UnsupportedJwtException e) {
			logger.error("token no soportado");
		}catch (ExpiredJwtException e) {
			logger.error("token expirado");
		}catch (IllegalArgumentException e) {
			logger.error("token vacío");
		}catch (SignatureException e) {
			logger.error("fallo en la firma");
		}
		
		return false;
	}
}
