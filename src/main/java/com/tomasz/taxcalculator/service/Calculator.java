package com.tomasz.taxcalculator.service;

import com.tomasz.taxcalculator.model.Person;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;


@Service
public class Calculator {

    DecimalFormat df = new DecimalFormat("####0.00");
    Person person;
    private double incomeValue;
    private double tax;
    private double taxCredit;
    private double USC;
    private double prsi;
    private double prsiRate = 0.04; //double type equivalent of 4%
    int weeksPerYear = 52; // Default number of weeks per year



    public Calculator(){}


    public double generateTax(Person newPerson){
        this.person = newPerson;
        this.incomeValue = newPerson.getWages();
        this.tax = incomeValue *0.2;
        this.taxCredit = generateTaxCredit(person.isInRelation());
        this.USC = generateUSC(person.getWages());
        this.prsi = generatePrsi(person.getWages());

        /*
        Still there is a mistate in this calculation.
        TaxCredit should be subtracted only from tax,
        not from the general result;
         */
        double result = (tax - taxCredit) + prsi + USC;

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

//        System.out.println("result / weekPerYear  = "+ result/weeksPerYear);
        return Math.round((result/weeksPerYear)*100.0)/100.0;
    }


    /*
    Calculate one-sixth of your earnings over €352.01. €377- €352.01 = €24.99. Divided by 6 = €4.17.
    Subtract this from the maximum credit of €12, giving you a credit of €7.83.
    The basic PRSI charge is 4% of €377 = €15.08.
    You will pay €7.25 PRSI weekly (€15.08 minus your €7.83 PRSI credit).
     */
    double generatePrsi(double incomeValue) {

        double prsi;

        if (incomeValue > 352) {
            double credit = (incomeValue - 352)/6;
            if(credit>12){
                credit = 12;
            }else{
                credit = 12-credit;
            };

            double charge = incomeValue * prsiRate;
            prsi = charge - credit;
        } else
            prsi = 0;

        return Math.round(prsi*100.0)/100.0;
    }

    /**
     * This generator is very cintractual and not accurate.
     * It bases on annual income calculated from the weekly income
     * multiplyed by the number of week per year which corresponds
     * with the reality only in cases when we have stable weekly wages.
     * @param income
     * @return
     */
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

            /*
            If the amount is still higher than 0
            then:
             */
            if(0<annualIncome){
                result += annualIncome*0.08;
            }
        }

        return Math.round(result/weeksPerYear*100.0)/100.0;
    }


    double getUSC() {
        return USC;
    }
    double getTax() {
        return tax;
    }


    public void setIncomeValue(double incomeValue){
        this.incomeValue = incomeValue;
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
                "\n    prsi = " + df.format(generatePrsi(person.getWages())) +
                "\n    USC = " + df.format(getUSC()) +
                "\n    taxCredit = " + df.format(taxCredit) +
                "\n    final tax = " + df.format(generateTax(person));
    }
}
