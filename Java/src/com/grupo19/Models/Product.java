package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.Interfaces.IProduct;

public class Product implements IProduct {
    private String codigo;
    private int[] filialBought;

    public Product(){
        this.codigo= "";
        filialBought = new int[GereVendasModel.getNumFiliais()];
    }

    public Product(String a){
        this.codigo= a;
        filialBought = new int[GereVendasModel.getNumFiliais()];
    }

    public Product(Product x){
        this.codigo = x.getCodigo();
        filialBought = new int[x.filialBought.length];
        System.arraycopy(x.filialBought,0,this.filialBought,0,x.filialBought.length);
    }

    public String getCodigo(){
        return this.codigo;
    }


    public boolean isValid ( ) {
        return false;
    }

    public Product clone(){
        return new Product(this);
    }

    public boolean equals(Object o){ //TODO: EQUALS para o filialBought
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Product p = (Product) o;
        return (this.codigo.equals(p.getCodigo()));
    }
}
