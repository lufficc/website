package com.lufficc.repository;

import com.lufficc.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lcc_luffy on 2016/8/10.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByCode(String code);

}
