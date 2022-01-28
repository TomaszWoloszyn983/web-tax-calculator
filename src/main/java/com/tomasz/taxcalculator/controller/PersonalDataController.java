package com.tomasz.taxcalculator.controller;


import com.tomasz.taxcalculator.model.Person;
import com.tomasz.taxcalculator.model.PersonDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class PersonalDataController {
    private static final Logger logger = LoggerFactory.getLogger(PersonalDataController.class);
    private final PersonDataRepository repository;

    PersonalDataController(final PersonDataRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/people", params = {"!sort", "!page", "!size"})
    ResponseEntity<?> readPeopleList(){
        logger.warn("Displaying people list!");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/people/{id}")
    ResponseEntity<?> updateData(@PathVariable int id, @RequestBody @Valid Person toUpdate){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        logger.info("Updating "+toUpdate.getName()+"s data");
        toUpdate.setId(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
            /*
            Na chwilę obecną jest pewien błąd w tej metodzie.
            Korzysta ona z metody save() i powoduje to że poprzez
            updatowania niestniejącego elementu o jakimś wymyślonym
            id taki element zostaje utworzony.

            Zapobiegliśmy powyższemu poprzez dodanie if'a oraz
            poprzez pobranie id'ka z updatowanego elementu(pathVariable).
            Dodaliśmy także upadte id'ka na ten pobrany z adresu.
             */
    }

    @PostMapping("/people")
    ResponseEntity<Person> addPerson(@RequestBody @Valid Person newPerson){
        if(repository.existsByName(newPerson.getName())){
            logger.warn("Person "+newPerson.getName()+" already exists. Change name");
            return ResponseEntity.notFound().build();
        }
        logger.info("Adding "+newPerson.getName()+" to the repository");
    repository.save(newPerson);
    return ResponseEntity.ok().build();
    }

    @GetMapping("/people/{id}")
    ResponseEntity<?> readPerson(@PathVariable int id){
        return ResponseEntity.ok(repository.findById(id));
    }

    /**
     * Modified version of the Patch method which saves the previous values of the variables if a new
     * value is not given.
     *
     * @param id
     * @param toUpdate
     * @return
     */
    @PatchMapping("/people/{id}")
    ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody @Valid Person toUpdate){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

//        Person temp = repository.findById(id).get();

//        if(toUpdate.getDateOfBirth()!=null){
//            temp.setDateOfBirth(toUpdate.getDateOfBirth());
//        }
//        if(toUpdate.getWages()!=0){
//            temp.setWages(toUpdate.getWages());
//        }
//        if(toUpdate.getDateOfBirth()!=null){
//            temp.setDateOfBirth(toUpdate.getDateOfBirth());
//        }
//        temp.setId(id);
//        repository.save(temp);

        repository.findById(id).
                ifPresent(person -> {
                            person.updateFrom(toUpdate);
                            repository.save(person);
                        });


        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/people")
//    ResponseEntity<?> readPeopleList(Pageable page){
//        logger.warn("Displaying people list!");
//        return ResponseEntity.ok(repository.findAll());
//    }
}
