package com.example.smart_splitter.repository;


import com.example.smart_splitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<User, UUID> {

    @Query(value = """
        SELECT *
        FROM users
        ORDER BY created_at DESC
    """, nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = """
        SELECT *
        FROM users
        WHERE id = :id
    """, nativeQuery = true)
    Optional<User> findUserById(UUID id);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
