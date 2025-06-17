package com.example.TaskProject.securityService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTtokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		
		if(StringUtils.hasText(token)&&jwtTokenProvider.validate(token)){
			String email= jwtTokenProvider.getEmailFromToken(token);
			log.info("email2 {}",email);
		UserDetails userDetails =	customUserDetailsService.loadUserByUsername(email);
		  UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		  log.info("UserDetails {}",userDetails);
		}
		
	filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request){
		String token=request.getHeader("Authorization");
		
		if(StringUtils.hasText(token)&&token.startsWith("Bearer")) {
			 return token.substring(7, token.length());
		}
		return null;
		
	}

}
