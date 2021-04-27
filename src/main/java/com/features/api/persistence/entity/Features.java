package com.features.api.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feature_name")
    private String featureName;

    private String email;
    private Boolean enable;
}
