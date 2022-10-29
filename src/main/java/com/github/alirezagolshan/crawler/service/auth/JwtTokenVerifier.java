package com.github.alirezagolshan.crawler.service.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.alirezagolshan.crawler.util.Constants.*;

public class JwtTokenVerifier extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var authHeader = request.getHeader(AUTHORIZATION);
    if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith(JWT_BEARER)) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      var token = authHeader.replace(JWT_BEARER, "");
      var claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(JWT_SECRET.getBytes())).parseClaimsJws(token);
      var body = claimsJws.getBody();
      var username = body.getSubject();
      var authorities = (List<Map<String, String>>) body.get(AUTHORITIES);
      var simpleGrantedAuthorities = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toList());
      var authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      throw new IllegalStateException(INVALID_TOKEN_MESSAGE);
    }
    filterChain.doFilter(request, response);
  }
}
