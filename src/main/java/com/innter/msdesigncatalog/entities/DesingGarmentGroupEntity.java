package com.innter.msdesigncatalog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_desing_garments_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesingGarmentGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_name")
    private String name;

    @Column(name = "fc_code")
    private String code;

    @Column(name = "fc_garment_location")
    private String garmentLocation;

    @Column(name = "fb_status")
    private Boolean status = true;

    @OneToMany(mappedBy = "garmentsGroup")
    Set<DesingPriceCompositionGroupEntity> desingPriceCompositionGroup;
}
