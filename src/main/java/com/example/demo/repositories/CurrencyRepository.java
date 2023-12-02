package com.example.demo.repositories;

import com.example.demo.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    public Currency findTopByOrderByIdDesc();
}
