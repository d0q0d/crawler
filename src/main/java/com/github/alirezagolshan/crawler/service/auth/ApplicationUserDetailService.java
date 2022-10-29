package com.github.alirezagolshan.crawler.service.auth;

import com.github.alirezagolshan.crawler.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.alirezagolshan.crawler.util.Constants.USERNAME_NOT_FOUND;

@Service
public class ApplicationUserDetailService implements UserDetailsService {

  private final UserService userService;

  public ApplicationUserDetailService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userService.findByUsername(username);
    if (user == null) throw new UsernameNotFoundException(USERNAME_NOT_FOUND);
    return new ApplicationUser(getGrantedAuthorities(user), user.getPassword(), user.getUsername());
  }

  private Set<GrantedAuthority> getGrantedAuthorities(User user) {
    var grantedAuthorities = new HashSet<GrantedAuthority>();
    var roles =
        user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    grantedAuthorities.addAll(roles);
    return grantedAuthorities;
  }
}
