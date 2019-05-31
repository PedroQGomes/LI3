package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.Interfaces.IProduct;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public boolean isValid () {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[1-9][0-9]{3}$");
        Matcher matcher = pattern.matcher(this.getCodigo());
        return matcher.matches();
    }

    public void updateProductBought (int filial,int qnt) {
        filialBought[filial] += qnt;
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

    @Override
    public String toString ( ) {
        StringBuilder sb = new StringBuilder(this.getCodigo());
        return sb.toString();
    }
}
