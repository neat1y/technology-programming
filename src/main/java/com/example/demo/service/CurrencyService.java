package com.example.demo.service;

import com.example.demo.models.Currency;
import com.example.demo.models.Operations;
import com.example.demo.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository =currencyRepository;
    }
    @Transactional
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    public Currency findLast() {
        return currencyRepository.findTopByOrderByIdDesc();
    }
}
