package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.GereVendasView;
import com.grupo19.Interfaces.IClient;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements IClient {
    private String codigo;
    private boolean[] boughtOnFilial;



    public Client(){
        this.codigo = "";
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }

    public Client(String s){
        this.codigo = s;
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }

    public Client(Client a){
        this.codigo = a.getCodigo();
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
        System.arraycopy(a.boughtOnFilial,0,this.boughtOnFilial,0,a.boughtOnFilial.length);
    }

    public void updateClientBought (int filial) {
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

    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Client p = (Client) o;
        return (this.codigo.equals(p.getCodigo()) && Arrays.equals(p.boughtOnFilial,this.boughtOnFilial) );

    }

   

}
