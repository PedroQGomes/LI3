package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.Interfaces.IClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.arraycopy(a.filialBought,0,this.filialBought,0,a.filialBought.length);

    }

    public void updateClientBought (int filial) {
        filialBought[filial] = 1;
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

    public boolean equals(Object o){ //TODO: EQUALS para o filialBought
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Client p = (Client) o;
        return (this.codigo.equals(p.getCodigo()));

    }

   

}
