package com.oksana.library.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "author")
public class Author extends BaseEntity {
    @Column(name = "name")
    private String name;

}
