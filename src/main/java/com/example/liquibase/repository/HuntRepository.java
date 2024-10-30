package com.example.liquibase.repository;

import com.example.liquibase.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuntRepository extends JpaRepository<Hunt, Integer> {
}
