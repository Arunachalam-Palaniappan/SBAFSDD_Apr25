package com.training.springSample.Service;

import com.training.springSample.Model.Product;
import com.training.springSample.Repository.MainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    final private MainRepository repo;

    public MainService(MainRepository repo) {
        this.repo = repo;
    }

    //Added for Pagination
    public Page<Product> getAllProducts(Pageable pageable) {
        return repo.findAll(pageable);
    }

    //Added for Filter
    public Page<Product> filterProducts(String keyword, Pageable pageable) {
        return repo.findByPrdnameContainingIgnoreCase(keyword,pageable);
    }
}
