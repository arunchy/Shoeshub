package com.example.database.repository;

import com.example.database.pojo.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {

}
