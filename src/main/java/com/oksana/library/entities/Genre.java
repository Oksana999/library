package com.oksana.library.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "genres")
public class Genre extends BaseEntity {

    @Column(name = "name")
    private String name;

}
