package com.oksana.library.entities;

import com.oksana.library.Language;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column
    private String description;

    @Column
//    @Enumerated(EnumType.STRING)
    private Language language;

}
