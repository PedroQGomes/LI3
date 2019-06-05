package com.grupo19.Models;

import com.grupo19.Interfaces.IClient;
import com.grupo19.Interfaces.IFilial;
import com.grupo19.Interfaces.ISale;
import com.grupo19.Tuple;

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

<<<<<<< HEAD
=======
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
>>>>>>> 8896ea9bb6ac4b399b42ea94d5020ba071d41321

    // queries 2 interativa e tb querie estatica
    // numero total de vendas num mes
    public Tuple<Integer,Integer> totalNumbOfSalesInMonthAndClientsBought(int x){
        if(x > 11 || x < 0){return null;}
        int res = 0;
        Set<String> client = new HashSet<>();
        for(Map.Entry<String,List<List<ISale>>> lista : this.filial.entrySet()){
            List<ISale> tmp = lista.getValue().get(x);
            res += tmp.size();
            for(ISale sale: tmp ) {
                client.add(sale.getClient());

            }
        }
        return new Tuple<>(res,client.size());
    }

    public double totalFaturadoPerClientPerMonth(String client,int month) {
        double sum = 0.0;
        List<ISale> tmp = this.filial.get(client).get(month);
            for(ISale sale : tmp) {
                sum = sale.totalPrice();
            }
        return sum;
    }

    // querie 3 interativa
    // retorna o numero de produtos distitnos comprados por um cliente num dado mes
    public Tuple<Integer,Set<String>> numOfDifferentProductsOfClientAndNumOfSales(String cliente,int mes){
        if(mes > 11 || mes < 0){return null;}
        List<ISale> tmp = this.filial.get(cliente).get(mes);
        Set<String> rep = new HashSet<>();
        for (ISale sale : tmp ){
            rep.add(sale.getProduct());
        }
        return new Tuple<>(tmp.size(),rep);
    }


    // metodo auxiliar
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

    public Map<String,Integer> getListOfProductsBoughtOfClient(String client) {
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

        return mapa;
    }

    // queire 7 interativa
    // determinar a lista de tres mairores compradores em termos de dinheiro faturado
    public List<String> getListOfClientsWhoMostBought(){
        Map<String,Double> mapa = new HashMap<>();
        for(Map.Entry<String,List<List<ISale>>> lista : this.filial.entrySet()){
            for(int i = 0;i<12;i++){
                for(ISale sale :lista.getValue().get(i)){
                    if(mapa.containsKey(sale.getClient())){
                        double tmp = mapa.get(sale.getClient());
                        tmp += sale.getPrice();
                        mapa.put(sale.getClient(),tmp);
                    }else {
                        mapa.put(sale.getClient(),sale.getPrice());

                    }
                }

            }
        }
        return mapa.entrySet().stream().sorted((o1,o2)-> o1.getValue().compareTo(o2.getValue())).map(l-> l.getKey()).limit(3).collect(Collectors.toList());
    }

}
