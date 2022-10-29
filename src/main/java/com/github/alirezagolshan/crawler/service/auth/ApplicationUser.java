package com.github.alirezagolshan.crawler.service.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ApplicationUser implements UserDetails {

  private final Collection<? extends GrantedAuthority> grantedAuthorities;
  private final String password;
  private final String username;

  public ApplicationUser(Collection<? extends GrantedAuthority> grantedAuthorities, String password, String username) {
    this.grantedAuthorities = grantedAuthorities;
    this.password = password;
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
