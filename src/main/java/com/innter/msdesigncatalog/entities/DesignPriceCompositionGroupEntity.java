package com.innter.msdesigncatalog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Table(name = "tb_design_prices_composition_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignPriceCompositionGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fi_id_garments_group")
    private DesignGarmentGroupEntity garmentsGroup;

    @ManyToOne
    @JoinColumn(name = "fi_id_compositions_group")
    private DesignCompositionGroupEntity compositionGroup;

    @Column(name = "fd_price")
    private BigDecimal price;

    @Column(name = "fb_status")
    private Boolean status = true;

}
