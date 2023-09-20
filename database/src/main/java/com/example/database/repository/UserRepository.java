package com.example.database.repository;

import com.example.database.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long>{
List<User> findByEmail(String email);


}
