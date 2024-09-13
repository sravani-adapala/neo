package com.neoteric.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="adhar",schema="bank")
@Data
public class AdharEntity {

    public AdharEntity(){     // No Arguement Constructor

    }

    @Id
    @Column(name = "adharNumber")
    public Integer adharNumber;

    @Id
    @Column(name = "name")
    public String name;

    @OneToMany
    public List<AddressEntity> addressEntities;
}
