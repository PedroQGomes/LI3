package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.Interfaces.IProduct;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product implements IProduct {
    private String codigo;
    private int[] boughtPerFilial;
    private boolean[] boughtOnFilial;
    public Product(){
        this.codigo= "";
        boughtPerFilial = new int[GereVendasModel.getNumFiliais()];
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }


    public char firstLetter ( ) {
        return codigo.charAt(0);
    }

    public Product(String a){
        this.codigo= a;
        boughtPerFilial = new int[GereVendasModel.getNumFiliais()];
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }

    public Product(Product x){
        this.codigo = x.getCodigo();
        boughtPerFilial = new int[x.boughtPerFilial.length];
        boughtOnFilial = new boolean[x.boughtOnFilial.length];
        System.arraycopy(x.boughtPerFilial,0,this.boughtPerFilial,0,x.boughtPerFilial.length);
        System.arraycopy(x.boughtOnFilial,0,this.boughtOnFilial,0,x.boughtOnFilial.length);
    }


    public boolean isProductEverBought ( ) {
        boolean tmp = false;
        for(boolean a : this.boughtOnFilial) {
            if(tmp) break;
            tmp = a;
        }
        return tmp;
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
        boughtOnFilial[filial] = true;
        boughtPerFilial[filial] += qnt;
     }

    public Product clone(){
        return new Product(this);
    }

    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Product p = (Product) o;
        return (this.codigo.equals(p.getCodigo()) && Arrays.equals(p.boughtOnFilial,this.boughtOnFilial) && Arrays.equals(p.boughtPerFilial,this.boughtPerFilial));
    }


}
