package com.training.springSample.Controller;

import com.training.springSample.Exception.ProductNotFoundException;
import com.training.springSample.Model.Product;
import com.training.springSample.Repository.MainRepository;
import com.training.springSample.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {
    final private MainService mainService;
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    private Environment environment;

    @Autowired
    private MainRepository repo;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to springSample application";
    }

    @GetMapping("/welcomeEnv")
    public String welcomeEnv() {
        return environment.getProperty("welcome.msg");
    }

    @GetMapping("/count")
    public ResponseEntity<String> count() {
        long count = repo.count();
        return new ResponseEntity<>("Products present:"+count, HttpStatus.OK);
    }

    @GetMapping("/products")
    public Iterable<Product> products() {
        Iterable<Product> products = repo.findAll();
        return products;
    }

    @GetMapping("/productById")
    public Product productById(@RequestParam int id) {
        Optional<Product> product = repo.findById(id);
        return product.orElse(null);
    }

    //Added for Exception Handling
    @GetMapping("/productByIdExcp/{id}")
    public Product productByIdExcp(@PathVariable int id) {
        Optional<Product> product = repo.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct() {
        Product product = new Product();
        product.setPrdname("iPhone 14");
        product.setPrdcount("10");
        product.setPrddescription("Apple iPhone mobile device");
        product.setPrdprice("55000");
        repo.save(product);
        return new ResponseEntity<String>("New product added succesfully, product: "+product.getPrdname(),HttpStatus.OK);
    }

    @PostMapping("/addProductReq")
    public ResponseEntity<String> addProductReq(@RequestBody Product product) {
        repo.save(product);
        return new ResponseEntity<String>("New product added succesfully through URI, product: "+product.getPrdname(),HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product product1 = repo.findById(id).orElseThrow();
        product1.setPrdname(product.getPrdname());
        product1.setPrdcount(product.getPrdcount());
        product1.setPrddescription(product.getPrddescription());
        product1.setPrdprice(product.getPrdprice());
        repo.save(product1);
        return new ResponseEntity<>("Product Details updated successfully for the product: "+product1.getPrdname(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        repo.deleteById(id);
        return new ResponseEntity<String>("Product deleted successfully, product id: "+id,HttpStatus.OK);
    }
}


