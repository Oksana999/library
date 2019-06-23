package com.oksana.library.repositories;

import com.oksana.library.entities.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUserEmail(String userEmail);
}
