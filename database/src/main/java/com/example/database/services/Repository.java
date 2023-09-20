package com.example.database.services;


import com.example.database.pojo.Brands;
import com.example.database.pojo.Shoes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@org.springframework.stereotype.Repository

public class Repository {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Shoes> findShoesByBrand(Brands brands){
        String queryString="SELECT s FROM Shoes s WHERE s.shoeBrand= :brand";
        return entityManager.createQuery(queryString,Shoes.class).setParameter("brand",brands).getResultList();
    }
}
