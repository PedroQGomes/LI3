package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.Interfaces.IClient;

public class Client implements IClient {
    private String codigo;
    private int[] filialBought;

    public Client(){
        this.codigo = "";
        filialBought = new int[GereVendasModel.getNumFiliais()];
    }

    public Client(String s){
        this.codigo = s;
        filialBought = new int[GereVendasModel.getNumFiliais()];
    }

    public Client(Client a){
        this.codigo = a.getCodigo();
        filialBought = new int[a.filialBought.length];
        for(int i : a.filialBought){
            filialBought[i] = a.filialBought[i];
        }
    }



    public String getCodigo(){
        return this.codigo;
    }

    public Client clone(){
        return new Client(this);
    }

    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Client p = (Client) o;
        return (this.codigo.equals(p.getCodigo()));

    }

   

}
