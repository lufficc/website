package com.lufficc;

import com.lufficc.model.Role;
import com.lufficc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class WebsiteApplication implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (roleRepository.findByCode(Role.ROLE_USER.getCode()) == null) {
            roleRepository.save(Role.ROLE_USER);
        }
        if (roleRepository.findByCode(Role.ROLE_ADMIN.getCode()) == null) {
            roleRepository.save(Role.ROLE_ADMIN);
        }
        if (roleRepository.findByCode(Role.ROLE_SYSADMIN.getCode()) == null) {
            roleRepository.save(Role.ROLE_SYSADMIN);
        }
    }
}
