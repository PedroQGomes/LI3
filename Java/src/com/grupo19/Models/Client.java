package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.GereVendasView;
import com.grupo19.Interfaces.IClient;
import com.grupo19.Interfaces.IProduct;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements IClient, Serializable {
    private String codigo;
    private Set<String> productBought;
    private boolean[] boughtOnFilial;



    public Client(){
        this.codigo = "";
        productBought = new HashSet<>(70);
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }

    public Client(String s){
        this.codigo = s;
        productBought = new HashSet<>(70);
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }

    public Client(Client a){
        this.codigo = a.getCodigo();
        productBought = new HashSet<>(70);
        productBought.addAll(a.productBought);
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
        System.arraycopy(a.boughtOnFilial,0,this.boughtOnFilial,0,a.boughtOnFilial.length);
    }

    public void updateClientBought (int filial,String product) {
        this.productBought.add(product);
        boughtOnFilial[filial] = true;
    }

    public boolean hasClientEverBought () {
        boolean tmp = true;
        for(boolean a : this.boughtOnFilial) {
            if(!tmp) break;
            tmp = a;
        }
        return tmp;
    }

    public String getCodigo(){
        return this.codigo;
    }


    public boolean isValid () {
        Pattern pattern = Pattern.compile("^[A-Z][1-9][0-9]{3}$");
        Matcher matcher = pattern.matcher(this.getCodigo());
        return matcher.matches();
    }

    public Client clone(){
        return new Client(this);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode( new Object[] { this.boughtOnFilial ,this.codigo} );
    }

    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Client p = (Client) o;
        return (this.codigo.equals(p.getCodigo()) && Arrays.equals(p.boughtOnFilial,this.boughtOnFilial));

    }

    public int NumDiffProductsBought() {
        return this.productBought.size();
    }

   

}
