package com.revature.p1.banking.Models;

import com.revature.p1.banking.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class UserSecurity {
    private final User user;

    public UserSecurity(User user) { this.user = user; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).toList(); }
    @Override public String getPassword()               { return user.getPassword(); }
    @Override public String getUsername()               { return user.getUsername(); }
    @Override public boolean isAccountNonExpired()      { return true; }
    @Override public boolean isAccountNonLocked()       { return true; }
    @Override public boolean isCredentialsNonExpired()  { return true; }
    @Override public boolean isEnabled()                { return true; }
}
