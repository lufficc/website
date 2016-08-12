package com.lufficc.security;

import com.lufficc.model.User;
import com.lufficc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by lcc_luffy on 2016/8/10.
 */
@Service
public class AccountUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User not authorized.");
        }
        Collection<GrantedAuthority> grantedAuthorities
                = user
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toCollection(ArrayList::new));
        return new WebsiteUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                !user.isExpired(),
                !user.isCredentialsExpired(),
                !user.isLocked(),
                grantedAuthorities
        );
    }
}
