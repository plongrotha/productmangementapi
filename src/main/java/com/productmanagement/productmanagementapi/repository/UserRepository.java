package com.productmanagement.productmanagementapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.productmanagement.productmanagementapi.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    @Query(value = "SELECT email FROM users", nativeQuery = true)
    Optional<List<String>> findAllEmail();

}
