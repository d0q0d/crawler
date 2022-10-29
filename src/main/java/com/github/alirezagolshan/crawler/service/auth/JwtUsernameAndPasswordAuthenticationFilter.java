package com.github.alirezagolshan.crawler.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static com.github.alirezagolshan.crawler.util.Constants.*;

public class JwtUsernameAndPasswordAuthenticationFilter
    extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      var usernamePasswordAuthenticationRequest =
          new ObjectMapper()
              .readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);
      var usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(
              usernamePasswordAuthenticationRequest.getUsername(),
              usernamePasswordAuthenticationRequest.getPassword());
      return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) {
    var token =
        Jwts.builder()
            .setSubject(authResult.getName())
            .claim(AUTHORITIES, authResult.getAuthorities())
            .setIssuedAt(new Date())
            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(4)))
            .signWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes()))
            .compact();
    response.addHeader(AUTHORIZATION, JWT_BEARER + token);
  }
}
