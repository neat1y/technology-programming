package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="operations")
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sell")
    private double sell ;
    @Column(name = "buy")
    private double buy;
    @Column(name = "operation_date")
    private LocalDateTime operation_Date;
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private Person person;
    @Column(name = "euro")
    private Boolean euro;

    @Column(name = "dollar")
    private Boolean dollar;

    public Boolean getEuro() {
        return euro;
    }

    public void setEuro(Boolean euro) {
        this.euro = euro;
    }

    public Boolean getDollar() {
        return dollar;
    }

    public void setDollar(Boolean dollar) {
        this.dollar = dollar;
    }

    @ManyToOne
    @JoinColumn(name ="currency_id", referencedColumnName = "id")
    private Currency currency;

    public Operations(double sell, double buy, LocalDateTime operation_Date) {
        this.sell = sell;
        this.buy = buy;
        this.operation_Date = operation_Date;
    }

    public Operations() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public LocalDateTime getOperation_Date() {
        return operation_Date;
    }

    public void setOperation_Date(LocalDateTime operation_Date) {
        this.operation_Date = operation_Date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
