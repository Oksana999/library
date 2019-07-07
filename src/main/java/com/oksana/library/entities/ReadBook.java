package com.oksana.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "read_books")
public class ReadBook extends BaseEntity{

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;

    @JoinColumn(name = "book_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Book book;

    @Column(name = "date")
    private LocalDateTime date;
}
