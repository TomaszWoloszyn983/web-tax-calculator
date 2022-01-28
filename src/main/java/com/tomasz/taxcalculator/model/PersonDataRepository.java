package com.tomasz.taxcalculator.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

public interface PersonDataRepository {
    List<Person> findAll();

    Optional<Person> findById(Integer id);

    Person save(Person entity);

    Page<Person> findAll(Pageable page);

    boolean existsById(Integer id);

    boolean existsByName(String name);



    @RestResource(path = "inRelation", rel = "inRelation")
    List<Person> findByInRelation(boolean inRelation);

    @RestResource(path = "name", rel = "name")
    List<Person> findByName(@Param("name") String name);
}
