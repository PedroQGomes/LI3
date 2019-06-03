package com.grupo19.Models;

import com.grupo19.Interfaces.IClient;
import com.grupo19.Interfaces.IFilial;
import com.grupo19.Interfaces.ISale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // idk se está bem
    public Map<String,List<List<ISale>>> getFilial(){
        Map<String,List<List<ISale>>> tmp = new HashMap<>();
        for(Map.Entry<String,List<List<ISale>>> mapa : this.filial.entrySet()){
            tmp.put(mapa.getKey(),cloneOfLists(mapa.getValue()));
        }
        return tmp;
    }

    private List<List<ISale>> cloneOfLists(List<List<ISale>> init){
        List<List<ISale>> res = new ArrayList<>();
        for(int i = 0; i <12; i++) {
            res.add(i,init.get(i).stream().map(ISale::clone).collect(Collectors.toList()));
        }
        return res;
    }

    public void add(ISale a){
        if(!this.filial.containsKey(a.getClient())) {
            List<ISale> s = new ArrayList<>();
            s.add(a.clone());
            List<List<ISale>> lista = new ArrayList<>(12);
            lista.add(a.getMonth(),s);
            this.filial.put(a.getClient(),lista);
        }
        List<List<ISale>> monthArr = this.filial.get(a.getClient());
        List<ISale> saleArr = monthArr.get(a.getMonth()-1);
        saleArr.add(a.clone());

    }

    // da o numero de sales de um cliente num certo mes
    public int getNumberOfSalesPerClientPerMonth(IClient client , int month) {
        return this.filial.get(client.getCodigo()).get(month).size();
    }

    // da o numero de vendas de um cliente num ano
    public int getNumberOfSalesPerClientPerYear(IClient client){
        int sum = 0;
        for(int i = 0; i<12; i++){
            sum += this.filial.get(client.getCodigo()).get(i).size();
        }
        return sum;
    }

}
