package com.tomasz.taxcalculator.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "people", collectionResourceRel = "people")
public interface PersonDataRepository extends JpaRepository<Person, Integer> {

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Person entity);

    @RestResource(path = "inRelation", rel = "inRelation")
    List<Person> findByInRelation(@Param("state") boolean inRelation);

    @RestResource(path = "name", rel = "name")
    List<Person> findByName(@Param("name") String name);
}
