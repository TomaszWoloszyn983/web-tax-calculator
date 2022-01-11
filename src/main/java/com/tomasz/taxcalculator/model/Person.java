package com.tomasz.taxcalculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "person")
class Person {
    @Id
    private UUID id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private double wages;
    private boolean inRelation;



    UUID getId() {
        return id;
    }

    void setId(UUID id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge(){
        return java.time.LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    double getWages() {
        return wages;
    }

    void setWages(double wages) {
        this.wages = wages;
    }

    boolean isInRelation() {
        return inRelation;
    }

    void setInRelation(boolean inRelation) {
        this.inRelation = inRelation;
    }


}
