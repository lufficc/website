package com.lufficc.service;

import com.lufficc.model.Role;
import com.lufficc.model.User;
import com.lufficc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public long count() {
        return userRepository.count();
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createNewUser(String username, String email, String password) {
        if (userRepository.findByEmail(email) != null || userRepository.findByUsername(username) != null) {
            return null;
        }
        User user = new User(username, email, passwordEncoder.encode(password));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        if ("lufficc".equals(username) && "528360256@qq.com".equals(email)) {
            roles.add(Role.ROLE_ADMIN);
            roles.add(Role.ROLE_SYSADMIN);
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        if (user.getId() == null) {
            return;
        }
        userRepository.save(user);
    }
}
