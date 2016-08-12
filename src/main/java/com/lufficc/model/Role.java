package com.lufficc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by lcc_luffy on 2016/8/10.
 */
@Entity
public class Role {

    public static final Role ROLE_USER = new Role(1L, "ROLE_USER", "user");
    public static final Role ROLE_ADMIN = new Role(2L, "ROLE_ADMIN", "admin");
    public static final Role ROLE_SYSADMIN = new Role(3L, "ROLE_SYSADMIN", "system admin");

    @Id
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id.equals(role.id) && code.equals(role.code) && label.equals(role.label);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + label.hashCode();
        return result;
    }

    public Role() {
    }

    public Role(Long id, String code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;
    }

    @Override
    public String toString() {
        return code;
    }
}
