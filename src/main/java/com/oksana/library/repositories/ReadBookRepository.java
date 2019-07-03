package com.oksana.library.repositories;

import com.oksana.library.entities.ReadBook;
import com.oksana.library.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReadBookRepository extends Repository<ReadBook> {
    public Page<ReadBook> findAllByUser (User user, Pageable pageable);

}
