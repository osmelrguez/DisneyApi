package com.alkemy.disney.models;

import com.alkemy.disney.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class RoleModel implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private long roleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, unique = true)
    private RoleEnum roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    private List<UserModel> users;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
