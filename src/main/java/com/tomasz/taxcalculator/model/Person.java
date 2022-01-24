package com.tomasz.taxcalculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Persons name must not be empty")
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private double wages;
    private boolean inRelation;


//
//    public Person(int id, String name, LocalDate dateOfBirth, double wages, boolean inRelation) {
//        this.id = id;
//        this.name = name;
//        this.dateOfBirth = dateOfBirth;
//        this.wages = wages;
//        this.inRelation = inRelation;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getAge(){
        return java.time.LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWages() {
        return wages;
    }

    void setWages(double wages) {
        this.wages = wages;
    }

    public boolean isInRelation() {
        return inRelation;
    }

    void setInRelation(boolean inRelation) {
        this.inRelation = inRelation;
    }


}
