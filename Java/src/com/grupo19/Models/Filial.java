package com.grupo19.Models;

import com.grupo19.Interfaces.IClient;
import com.grupo19.Interfaces.IFilial;
import com.grupo19.Interfaces.ISale;

import java.util.*;
import java.util.stream.Collectors;

public class Filial implements IFilial {

    private Map<String,List<List<ISale>>> filial;


    public Filial(){

        this.filial = new HashMap<>();

    }


    public Filial(IFilial a){
        this.filial = a.getFilial();
    }


    public IFilial clone() {
        return new Filial(this);
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
            List<List<ISale>> lista = new ArrayList<>(12);
            for(int i = 0; i<12;i++) {
                List<ISale> s = new ArrayList<>();
                lista.add(s);
            }
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

    // queire estatica
    // faturacao total da filial
    public double faturacaoTotal(){
        double res = 0;
        for(Map.Entry<String,List<List<ISale>>> lista : this.filial.entrySet()){
            for(int i = 0; i<12;i++) {
                res += lista.getValue().get(i).stream().mapToDouble(l-> l.getPrice()).sum();
            }
        }
        return res;
    }

    // querie estatica
    // faturacao total de um dado mes
    public double faturacaoOfMonth(int x){
        if(x > 12){return 0;}
        double res = 0;
        for(Map.Entry<String,List<List<ISale>>> lista : this.filial.entrySet()){
            res += lista.getValue().get(x-1).stream().mapToDouble(l-> l.getPrice()).sum();
        }
        return res;
    }

    // queire 2 interativa e tb queire estatica
    // numero de clientes distintos que compraram num certo mes
    public int numberOfClientsBoughtInMonth(int x){
        if(x > 12){return 0;}
        List<ISale> res = new ArrayList<>();
        for(Map.Entry<String,List<List<ISale>>> lista : this.filial.entrySet()){
            for(ISale sale : lista.getValue().get(x)) {
                if(firstSale(res,sale)){
                    res.add(sale);
                }
            }

        }
        return res.size();
    }

    // metodo aussiliar
    private boolean firstSale(List<ISale> lista, ISale sale){
        for(ISale s : lista){
            if(s.getClient().equals(sale.getClient())){

                return false;
            }
        }
        return true;
    }

    // queries 2 interativa e tb querie estatica
    // numero total de vendas num mes
    public int totalNumbOfSalesInMonth(int x){
        if(x > 12){return 0;}
        int res = 0;
        for(Map.Entry<String,List<List<ISale>>> lista : this.filial.entrySet()){
            res += lista.getValue().get(x).size();
        }
        return res;
    }

    // querie 3 interativa
    // determinar qnts compras fez num mes um dado cliente
    public int numOfClientsInAMonth(String cliente,int mes){
        return this.filial.get(cliente).get(mes).size();
    }

    // querie 3 interativa
    // retorna o numero de produtos distitnos comprados por um cliente num dado mes
    public int numOfDifferentProductsOfClient(String cliente,int mes){
        if(mes > 12){return 0;}
        List<ISale> res = new ArrayList<>();
        for (ISale sale : this.filial.get(cliente).get(mes)){
            if(!productExists(res,sale)){
                res.add(sale);
            }
        }
        return res.size();
    }


    // metodo aussiliar
    private boolean productExists(List<ISale> lista,ISale sale){
        for(ISale a : lista){
            if(a.getProduct().equals(sale.getProduct())){
                return true;
            }
        }
        return false;
    }


    //querie 3 interativa
    // diz qnt gastou um cliente num dado mes
    public double clientSpentInAMonth(String cliente,int mes){
        return this.filial.get(cliente).get(mes).stream().mapToDouble(l->l.getPrice()).sum();
    }


    // querie 5 interativa
    // lista de codigos dos produtos que comprou por ordem descrescente de quantidade
    // e para qnts iguais por ordem alfabetica

    public List<String> getListOfProductsBoughtOfClient(String client) {
        Map<String,Integer> mapa = new HashMap<>();
        List<String> lista;
        for(int i = 0; i < 12; i++){
            for(ISale sale :this.filial.get(client).get(i)){
                if(!mapa.containsKey(sale.getProduct())){
                    mapa.put(sale.getProduct(),1);
                }else {
                    int tmp = mapa.get(sale.getProduct());
                    tmp++;
                    mapa.put(sale.getProduct(),tmp);
                }
            }
        }
        lista = mapa.entrySet().stream().sorted((o1,o2)-> comparaEntrySets(o1,o2)).map(l-> l.getKey()).collect(Collectors.toList());
        Collections.reverse(lista);
        return lista;
    }

    // comparador das entrys
    private int comparaEntrySets(Map.Entry<String,Integer> fst,Map.Entry<String,Integer> snd){
        if(fst.getValue().equals(snd.getValue())){
            return fst.getKey().compareTo(snd.getKey());
        }
        if(fst.getValue() > snd.getValue()) return 1;
        return -1;
    }
    


}
