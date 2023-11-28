package com.innter.msdesigncatalog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_security_roles")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fi_id", nullable = false)
    private Long id;

    @Column(name = "fc_rol_name" ,nullable = false, length = 100)
    private String rolName;

    @Column(name = "fc_rol_section", nullable = false, length = 50)
    private String rolSection;

    @Column(name = "fc_rol_description" ,nullable = false, length = 100)
    private String rolDescription;

    @Column(name = "fb_status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "rol", cascade= CascadeType.ALL)
    private Set<UserRolEntity> usersRoles;
}
