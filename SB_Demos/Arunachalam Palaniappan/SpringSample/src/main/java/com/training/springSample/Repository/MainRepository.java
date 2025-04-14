package com.training.springSample.Repository;

import com.training.springSample.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<Product,Integer> {
    //Added for filtering product by name ignore case
    Page<Product> findByPrdnameContainingIgnoreCase(String prdname, Pageable pageable);
}
