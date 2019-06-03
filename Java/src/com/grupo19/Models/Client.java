package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.GereVendasView;
import com.grupo19.Interfaces.IClient;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements IClient {
    private String codigo;
    private int[] boughtPerFilial;
    private boolean[] boughtOnFilial;



    public Client(){
        this.codigo = "";
        boughtPerFilial = new int[GereVendasModel.getNumFiliais()];
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }

    public Client(String s){
        this.codigo = s;
        boughtPerFilial = new int[GereVendasModel.getNumFiliais()];
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
    }

    public Client(Client a){
        this.codigo = a.getCodigo();
        boughtPerFilial = new int[a.boughtPerFilial.length];
        boughtOnFilial = new boolean[GereVendasModel.getNumFiliais()];
        System.arraycopy(a.boughtPerFilial,0,this.boughtPerFilial,0,a.boughtPerFilial.length);
        System.arraycopy(a.boughtOnFilial,0,this.boughtOnFilial,0,a.boughtOnFilial.length);
    }

    public void updateClientBought (int filial) {
        boughtOnFilial[filial] = true;
        boughtPerFilial[filial] += 1;
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
        return (this.codigo.equals(p.getCodigo()) && Arrays.equals(p.boughtOnFilial,this.boughtOnFilial) && Arrays.equals(p.boughtPerFilial,this.boughtPerFilial));

    }

   

}
