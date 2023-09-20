package com.example.database.repository;

import com.example.database.pojo.Brands;
import com.example.database.pojo.Shoes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ShoesRepository extends CrudRepository<Shoes,Long> {

    List<Shoes> findFirstByOrderByShoeIdDesc();
    List<Shoes> findByshoeType(String type);
    List<Shoes> findByshoeBrand(Brands brand);

    @Query("SELECT s FROM Shoes s WHERE s.shoeName ILIKE %:name%")
    List<Shoes> findByShoeNameStartingWith(String name);




}
