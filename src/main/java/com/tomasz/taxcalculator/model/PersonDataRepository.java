package com.tomasz.taxcalculator.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface PersonDataRepository extends JpaRepository<Person, Integer> {
}
