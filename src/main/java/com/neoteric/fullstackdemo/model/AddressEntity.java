package com.neoteric.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="address",schema="bank")
@Data
public class AddressEntity {

    public AddressEntity(){     // No Arguement Constructor

    }

    @Id
    @Column(name = "id")
    public Integer id;

    @Id
    @Column(name = "state")
    public String state;

    @ManyToOne
    public AdharEntity adharEntity;

}
