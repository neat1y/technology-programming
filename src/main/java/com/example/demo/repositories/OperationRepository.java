package com.example.demo.repositories;

import com.example.demo.models.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operations,Integer> {
    public Optional<Operations> findTopByOrderByIdDesc();

}
