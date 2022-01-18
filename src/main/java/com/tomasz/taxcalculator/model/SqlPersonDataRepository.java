package com.tomasz.taxcalculator.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlPersonDataRepository extends PersonDataRepository, JpaRepository<Person, Integer> {

// Tymczasowo to komentuje. Jeśli okaże sie to zupełnie niepotrzebne to trzeba będzie całkiem wywalić.
//    @Override
//    @RestResource(exported = false)
//    void deleteById(Integer integer);
//
//    @Override
//    @RestResource(exported = false)
//    void delete(Person entity);

//    @RestResource(path = "inRelation", rel = "inRelation")
//    List<Person> findByInRelation(@Param("state") boolean inRelation);
//
//    @RestResource(path = "name", rel = "name")
//    List<Person> findByName(@Param("name") String name);
}
