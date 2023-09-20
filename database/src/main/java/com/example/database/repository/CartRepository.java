package com.example.database.repository;

import com.example.database.pojo.Cart;
import com.example.database.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart,Long> {
 List<Cart> findByuserDetails(User user);
@Transactional
 void deleteBycartId(Long cartId);

}
