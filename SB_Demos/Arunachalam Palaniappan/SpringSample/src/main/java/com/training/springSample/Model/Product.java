package com.training.springSample.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    int id;
    String prdname;
    String prdprice;
    String prddescription;
    String prdcount;

    public Product(int id) {
        this.id = id;
    }
    public Product() {

    }

    public int getId() {
        return id;
    }

    public String getPrdname() {
        return prdname;
    }

    public void setPrdname(String prdname) {
        this.prdname = prdname;
    }

    public String getPrdprice() {
        return prdprice;
    }

    public void setPrdprice(String prdprice) {
        this.prdprice = prdprice;
    }

    public String getPrddescription() {
        return prddescription;
    }

    public void setPrddescription(String prddescription) {
        this.prddescription = prddescription;
    }

    public String getPrdcount() {
        return prdcount;
    }

    public void setPrdcount(String prdcount) {
        this.prdcount = prdcount;
    }

    public void setId(int id) {
        this.id = id;
    }



}

