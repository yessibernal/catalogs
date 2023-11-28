package com.innter.msdesigncatalog.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_security_users_roles")
public class UserRolEntity {
    @EmbeddedId
    private UserRolKey id;

    @ManyToOne(fetch= FetchType.LAZY)
    @MapsId("id")
    @JoinColumn(name = "fi_id_user")
    private UserEntity user;

    @ManyToOne(fetch= FetchType.LAZY)
    @MapsId("id")
    @JoinColumn(name = "fi_id_rol")
    private RolEntity rol;
}
