package com.example.demo.models;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Entity
@Table(name ="currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id ;
    @Column(name = "sell_dollar")

    private double sell_dollar;

    @Column(name ="buy_dollar")
    private double buy_dollar;

    @Column(name = "sell_euro")

    private double sell_euro;

    @Column(name ="buy_euro")
    private double buy_euro;

    @Column(name="currencydate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate currencyDate;
    @OneToMany(mappedBy = "currency")
    private List<Operations> operations;

    public Currency() {
    }

    public Currency(double sell_dollar, double buy_dollar, double sell_euro, double buy_deuro, LocalDate currencyDate) {
        this.sell_dollar = sell_dollar;
        this.buy_dollar = buy_dollar;
        this.sell_euro = sell_euro;
        this.buy_euro = buy_deuro;
        this.currencyDate = currencyDate;
    }

    public double getSell_dollar() {
        return sell_dollar;
    }

    public void setSell_dollar(double sell_dollar) {
        this.sell_dollar = sell_dollar;
    }

    public double getBuy_dollar() {
        return buy_dollar;
    }

    public void setBuy_dollar(double buy_dollar) {
        this.buy_dollar = buy_dollar;
    }

    public double getSell_euro() {
        return sell_euro;
    }

    public void setSell_euro(double sell_euro) {
        this.sell_euro = sell_euro;
    }

    public double getBuy_euro() {
        return buy_euro;
    }

    public void setBuy_euro(double buy_deuro) {
        this.buy_euro = buy_deuro;
    }

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public LocalDate getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(LocalDate currencyDate) {
        this.currencyDate = currencyDate;
    }
}
