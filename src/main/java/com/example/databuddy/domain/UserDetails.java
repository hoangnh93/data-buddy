package com.example.databuddy.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetails extends User {

    private static final long serialVersionUID = -5013938934925448848L;

    private String userId;

    public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities, String userId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean hasAnyAuthority(String... roles) {
        final List<String> roleList = Arrays.asList(roles);

        return getAuthorities().stream().anyMatch(authority -> roleList.contains(authority.getAuthority()));
    }
}
