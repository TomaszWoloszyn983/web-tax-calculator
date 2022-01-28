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
    private String surname;
    @Embedded
    private Audit audit = new Audit();




    Person(){}
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
        if(dateOfBirth!=null) {
            return java.time.LocalDate.now().getYear() - dateOfBirth.getYear();
        }
        return 0;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }

    public boolean isInRelation() {
        return inRelation;
    }

    public void setInRelation(boolean inRelation) {
        this.inRelation = inRelation;
    }

    public String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Modified version of the Patch method which overwrites data with new values.
     * Saves the previous values of the variables if a new value was not given.
     * @param source
     */
    public void updateFrom(final Person source){
        if(source.getDateOfBirth()!=null){
            this.setDateOfBirth(source.getDateOfBirth());
        }
        if(source.getWages()!=0){
            this.setWages(source.getWages());
        }
        if(source.getDateOfBirth()!=null){
            this.setDateOfBirth(source.getDateOfBirth());
        }
        if(source.getSurname()!=null){
            this.setSurname(source.getSurname());
        }
        if(source.isInRelation()!=false){
            this.setInRelation(source.isInRelation());
        }
    }


}
