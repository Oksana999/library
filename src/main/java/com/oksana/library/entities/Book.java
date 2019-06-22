package com.oksana.library.entities;

import com.oksana.library.Language;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private List<Author> author;


}
