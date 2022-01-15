package com.tomasz.taxcalculator.controller;

import com.tomasz.taxcalculator.model.PersonDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RepositoryRestController
class PersonalDataController {
    private static final Logger logger = LoggerFactory.getLogger(PersonalDataController.class);
    private final PersonDataRepository repository;

    PersonalDataController(final PersonDataRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/people")
    ResponseEntity<?> readPeopleList(){
        logger.warn("Displaying people list!");
        return ResponseEntity.ok(repository.findAll());
    }
}
