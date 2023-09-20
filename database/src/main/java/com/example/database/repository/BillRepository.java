package com.example.database.repository;

import com.example.database.pojo.Bill;
import com.example.database.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill,Long> {
  List<Bill> findByuserDetails(User user);
}
