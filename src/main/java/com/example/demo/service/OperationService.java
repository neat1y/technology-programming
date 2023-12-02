package com.example.demo.service;

import com.example.demo.models.Operations;
import com.example.demo.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final CurrencyService currencyService;
    @Autowired
    public OperationService(OperationRepository operationRepository, CurrencyService currencyService) {
        this.operationRepository = operationRepository;
        this.currencyService = currencyService;
    }

    public void save(Operations operations) {
        operationRepository.save(operations);
    }

    public List<Operations> all() {
        return operationRepository.findAll();
    }

    public Optional<Operations> findByLast() {
        return operationRepository.findTopByOrderByIdDesc();
    }

    public List<Operations> time(LocalDate specificDate) {
        List<Operations> operations=operationRepository.findAll();
        List<Operations> fin=new ArrayList<>();
        LocalDate currentDate = currencyService.findLast().getCurrencyDate();
        for (Operations oper:operations) {
            LocalDate qwe= oper.getOperation_Date().atZone(ZoneId.systemDefault()).toLocalDate();
            if(qwe.getDayOfMonth() ==currentDate.getDayOfMonth()
                    && qwe.getMonthValue()== currentDate.getMonthValue()
                    && qwe.getYear() ==currentDate.getYear()){
                fin.add(oper);
            }
        }
        return fin;
    }
}
