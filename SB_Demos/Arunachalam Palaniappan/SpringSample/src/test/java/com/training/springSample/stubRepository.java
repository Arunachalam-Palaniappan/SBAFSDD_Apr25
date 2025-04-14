package com.training.springSample;

import com.training.springSample.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface stubRepository extends CrudRepository<Product, Integer> {
}
