package com.innter.msdesigncatalog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_desing_materials_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesingMaterialTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_name")
    private String name;

    @Column(name = "fc_code")
    private String code;

    @Column(name = "fc_type")
    private String type;

    @Column(name = "fc_classification")
    private String classification;

    @Column(name = "fb_status")
    private Boolean status = true;
}
