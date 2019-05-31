package com.grupo19.Models;

import com.grupo19.Interfaces.IProduct;

public class Product implements IProduct {
    private String codigo;


    public Product(){
        this.codigo= "";
    }

    public Product(String a){
        this.codigo= a;
    }

    public Product(Product x){
        this.codigo = x.getCodigo();
    }

    public String getCodigo(){
        return this.codigo;
    }

    public Product clone(){
        return new Product(this);
    }

    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Product p = (Product) o;
        return (this.codigo.equals(p.getCodigo()));
    }
}
