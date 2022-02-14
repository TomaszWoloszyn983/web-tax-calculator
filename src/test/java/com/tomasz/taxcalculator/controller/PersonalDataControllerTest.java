package com.tomasz.taxcalculator.controller;

import com.tomasz.taxcalculator.model.PersonDataRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * More test needed
 */
class PersonalDataControllerTest {

    @Test
    void readPersonsTax() {
        //given
        var mockPersonDataRepository = mock(PersonDataRepository.class);
        when(mockPersonDataRepository.existsByName(anyString())).thenReturn(true);
        // when
        // then
        assertTrue(mockPersonDataRepository.existsByName("Tomasz"));
    }

    @Test
    void existsById() {
        //given
        var mockPersonDataRepository = mock(PersonDataRepository.class);
        when(mockPersonDataRepository.existsById(anyInt())).thenReturn(true);
        // when
        // then
        assertTrue(mockPersonDataRepository.existsById(6));
    }


//    @Test
//    void findAll(){
//        //given
//        var mockPersonDataRepository = mock(PersonDataRepository.class);
//        when(mockPersonDataRepository.findAll()).thenReturn(List<Person>list);
//    }

    @Test
    void findByName(){
        //given
        var mockPersonDataRepository = mock(PersonDataRepository.class);
        when(mockPersonDataRepository.existsByName(anyString())).thenReturn(true);
        // when
        // then
        assertTrue(mockPersonDataRepository.existsByName("Tomasz"));
    }
}