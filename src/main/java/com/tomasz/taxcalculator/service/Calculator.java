package com.tomasz.taxcalculator.service;

import com.tomasz.taxcalculator.model.Person;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;


@Service
public class Calculator {

    DecimalFormat df = new DecimalFormat("####0.00");
    Person person;
    private double income;
    private double tax;
    double taxCredit;
    private double USC;
    private double prsi;
    private double prsiRate = 0.04; //double type equivalent of 4%
    int weeksPerYear = 52; // Default number of weeks per year



    public Calculator(){}


    public double generateTax(Person newPerson){
        this.person = newPerson;
        this.income = newPerson.getWages();
        this.tax = income*0.2;
        this.taxCredit = generateTaxCredit(person.isInRelation());
        this.USC = generateUSC(person.getWages());
        this.prsi = generatePrsi();

        double result = tax - taxCredit + prsi + USC;

        if(result>0){
            return result;
        }else{
            return 0;
        }
    }



    /**
     * Tax credits are used to reduce the amount of tax you pay.
     * The Personal Tax Credit you get depends on whether you are:
     *
     * single
     * married or in a civil partnership
     * widowed or a surviving civil partner
     * separated
     * divorced or a former civil partner.Tax credits are used to reduce the a
     *
     * @param inRelation
     * @return
     */
    double generateTaxCredit(boolean inRelation){
        double result = 1700; //Employee Tax Credit

        if(inRelation)
            result += 3400; //Married person Tax Credit
        else
            result += 1700; //Single person Tax Credit

        return result/ weeksPerYear;
    }


    /*
    Calculate one-sixth of your earnings over €352.01. €377- €352.01 = €24.99. Divided by 6 = €4.17.
    Subtract this from the maximum credit of €12, giving you a credit of €7.83.
    The basic PRSI charge is 4% of €377 = €15.08.
    You will pay €7.25 PRSI weekly (€15.08 minus your €7.83 PRSI credit).
     */
    double generatePrsi() {
        try {
            double prsi;

            if (income > 352) {
                double credit = (income-352)/6;
                if(credit>12){
                    credit = 12;
                }else{
                    credit = 12-credit;
                };

                double charge = income*prsiRate;
                prsi = charge - credit;
            } else
                prsi = 0;

            return prsi;

        }catch(NullPointerException e){
            System.out.println("Something went wrong! " +
                    "Propably the income variable hasn't been initialized.");
            e.printStackTrace();
        }
        return 0;
    }


    double generateUSC(double income){
        double result = 0;
        double annualIncome = income*weeksPerYear;

        if(12012<annualIncome){
            result += 12012*0.005;
            annualIncome-=12012;
        }
        if(9283<annualIncome){
            result += 9283*0.02;
            annualIncome-=9283;
        }
        if(49357<annualIncome){
            result += 49357*0.045;
            annualIncome-=49357;
        }
        if(0<annualIncome){
            result += annualIncome*0.08;
        }
        return result/weeksPerYear;
    }


    double getUSC() {
        return USC;
    }
    double getTax() {
        return tax;
    }


    public void setIncome(double income){
        this.income = income;
    }
    public void setWeeksPerYear(int newNumber){
        weeksPerYear = newNumber;
    }
    public void setPrsiRate(double prsi) {
        this.prsiRate = prsi/100;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "\n    income tax = " + df.format(getTax()) +
                "\n    prsi = " + df.format(generatePrsi()) +
                "\n    USC = " + df.format(getUSC()) +
                "\n    taxCredit = " + df.format(taxCredit) +
                "\n    final tax = " + df.format(generateTax(person));
    }
}
