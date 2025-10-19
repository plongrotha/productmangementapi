package com.productmanagement.productmanagementapi.repository;


import com.productmanagement.productmanagementapi.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    boolean existsByUsernameContainingIgnoreCaseAndPassword(String username, String password);

}
