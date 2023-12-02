package com.example.demo.controllers;

import com.example.demo.models.Currency;
import com.example.demo.models.Operations;
import com.example.demo.models.Person;
import com.example.demo.service.CurrencyService;
import com.example.demo.service.OperationService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonService personService;
    private final OperationService operationService;
    private final CurrencyService currencyService;
    @Autowired
    public AdminController(PersonService personService, OperationService operationService, CurrencyService currencyService) {
        this.personService = personService;
        this.operationService = operationService;
        this.currencyService = currencyService;
    }

    @GetMapping()
    public String vse(Model model){
        model.addAttribute("people",personService.all());
        return "auth/admin/logi";
    }
    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id,Model model){
        model.addAttribute("pers",personService.findById(id).get());
        return "auth/admin/index";
    }
    @GetMapping("/{id}/change")
    public String patch(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personService.findById(id).get());
        return "auth/admin/change";
    }
    @PostMapping("/{id}")
    public String index(@PathVariable("id") int id,@ModelAttribute("pers") Person person){
        personService.update(id,person);
        return "redirect:/admin";
    }
    @GetMapping("/newday")
    public String newday(@ModelAttribute("currency") Currency currency){
        return "auth/admin/newday";
    }

    @PostMapping("/newday")
    public String day(@ModelAttribute("currency") Currency currency){

        LocalDate currentDate = null;
        currentDate=currencyService.findLast().getCurrencyDate();
        currentDate=currentDate.plusDays(1);
        // Преобразуем LocalDateTime в Timestamp
        currency.setCurrencyDate(currentDate);
        currencyService.save(currency);
        return "redirect:/admin";
    }
    @GetMapping("/receipt")
    public String receipt(Model model){
        model.addAttribute("operarions", operationService.all());
        return "auth/admin/receipt";
    }
    @GetMapping("/receipt/analiz")
    public String abort(Model model){
        LocalDate currentDate = currencyService.findLast().getCurrencyDate();
        // Получаем день назад
        LocalDate yesterday = currentDate.minusDays(1);
        List<Operations> operationsList=operationService.time(currentDate);
        List<Operations> operationsList1=operationService.time(yesterday);
        Double money= Double.valueOf(0);
        Double money1= Double.valueOf(0);
        int i=0;
        int j=0;
        for (Operations operation:operationsList) {
            money+=operation.getBuy()+operation.getSell();
            i++;
        }
        for (Operations operation:operationsList1) {
            money1+=operation.getBuy()+operation.getSell();
            j++;
        }
        model.addAttribute("operations1",operationsList);
        model.addAttribute("operations2",operationsList1);
        model.addAttribute("money1",money1);
        model.addAttribute("money0",money);
        model.addAttribute("i",i);
        model.addAttribute("j",j);
        return"auth/admin/abort";
    }


}
