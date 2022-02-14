package com.tomasz.taxcalculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator underTest = new Calculator();

    @Test
    void generateTax() {
    }

    @Test
    void testGenerateTax() {
    }

    @Test
    @DisplayName("returns_tax_credit_for_a_single_person")
    void generateTaxCredit_returns_tax_credit_for_a_single_person() {
        //  given
        boolean inRelation  = false;
        // when
        double result = Math.round((underTest.generateTaxCredit(inRelation)));
        // then
        double expected = Math.round((3400/ underTest.weeksPerYear)*100.0)/100.0;
        assertThat(result).isEqualTo(expected);
    }
    @Test
    @DisplayName("returns_tax_credit_for_a_married_person")
    void generateTaxCredit_returns_tax_credit_for_a_married_person() {
        //  given
        boolean inRelation  = true;
        // when
        double result = Math.round((underTest.generateTaxCredit(inRelation)));
        // then
        double expected = Math.round((5100/ underTest.weeksPerYear)*100.00)/100.00;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generatePrsi_return_result() {
        //  given
        double income = 377.0;
        // when
        double result = underTest.generatePrsi(income);
        // then
        double expected = 7.25;
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void generatePrsi_should_return_0_result() {
        //  given
        double income = 325.0;
        // when
        double result = underTest.generatePrsi(income);
        // then
        double expected = 0;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generateUSC_for_a_wages_below_12K() {
        //  given
        double income = 455.22;
        // when
        double result = underTest.generateUSC(income);
        // then
        double expected = 4.73;
        assertThat(result).isEqualTo(expected);
    }
}