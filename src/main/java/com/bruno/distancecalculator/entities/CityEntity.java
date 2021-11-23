package com.bruno.distancecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cidade")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    private Integer uf;
    private Integer ibge;
    private Point lat_lon;
    private Double latitude;
    private Double longitude;
    private Integer cod_tom;
}
