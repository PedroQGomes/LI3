package com.grupo19.Models;

import com.grupo19.Interfaces.ISale;
import com.grupo19.Interfaces.IFilial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filial implements IFilial {

    private Map<String,List<List<ISale>>> filial;


    public Filial(){
        this.filial = new HashMap<>();
    }


    public Filial(Filial a){
        this.filial = a.getFilial();
    }

    @Override
    public IFilial clone() {
        return null;
    }

    // esta mal feita
    public Map<String,List<List<ISale>>> getFilial(){
        Map<String,List<List<ISale>>> tmp = new HashMap<>();
        return new HashMap<>();
    }

    public void add(ISale a){
        if(!this.filial.containsKey(a.getClient())) {
            List<ISale> s = new ArrayList<>();
            s.add(a.clone());
            List<List<ISale>> lista = new ArrayList<>(10);
            lista.add(a.getMonth(),s);
            this.filial.put(a.getClient(),lista);
        }
        List<List<ISale>> monthArr = this.filial.get(a.getClient());
        List<ISale> saleArr = monthArr.get(a.getMonth()-1);
        saleArr.add(a.clone());

    }
}
