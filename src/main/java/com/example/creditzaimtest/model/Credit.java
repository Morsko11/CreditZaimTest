package com.example.creditzaimtest.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "credit_pmt_string")
    private String paymentString;

    @Column(name = "credit_pmt_start")
    private LocalDate firstPaymentDate;

    // Конструкторы, геттеры и сеттеры
    public Credit() {
    }

    public Credit(String paymentString, LocalDate firstPaymentDate) {
        this.paymentString = paymentString;
        this.firstPaymentDate = firstPaymentDate;
    }

    public Long getId() {
        return id;
    }

    public String getPaymentString() {
        return paymentString;
    }

    public void setPaymentString(String paymentString) {
        this.paymentString = paymentString;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }
}