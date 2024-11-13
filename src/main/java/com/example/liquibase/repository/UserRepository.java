package com.example.liquibase.repository;

import com.example.liquibase.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(UUID id);

    List<User> findByUsernameContainingIgnoreCase(String username);

    List<User> findByLastName(String lastName);

    List<User> findByFirstName(String firstName);

    List<User> findByCin(String cin);

    @Query("SELECT DISTINCT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    List<User> findByEmail(@Param("email") String email);

    List<User> findByLicenseExpirationDateAfter(LocalDateTime now);

    Page<User> findAllByOrderByJoinDateDesc(Pageable pageable);

}
