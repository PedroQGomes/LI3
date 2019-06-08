package com.grupo19.Models;

import com.grupo19.Crono;
import com.grupo19.Interfaces.IClient;
import com.grupo19.Interfaces.IFilial;
import com.grupo19.Interfaces.ISale;
import com.grupo19.Interfaces.ITuple;
import com.grupo19.Tuple;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Filial implements IFilial, Serializable {

    private Map<String,List<List<ISale>>> filialData;


    /**
     * constrotor vazio da filial
     */
    public Filial(){

        this.filialData = new HashMap<>();

    }

    /**
     * constrotor parametrizado da filial
     * @param a
     */
    public Filial(IFilial a){
        this.filialData = a.getFilialData();
    }


    /**
     * faz um clone da filial
     * @return IFilial
     */
    public IFilial clone() {
        return new Filial(this);
    }

    /**
     * metodo que faz o get da filial completamente clonado
     * @return tmp
     */
    public Map<String,List<List<ISale>>> getFilialData(){
        Map<String,List<List<ISale>>> tmp = new HashMap<>();
        for(Map.Entry<String,List<List<ISale>>> mapa : this.filialData.entrySet()){
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

    /**
     * metodo quee adiciona uma sala a filial
     * @param a
     */
    public void add(ISale a){
        if(!this.filialData.containsKey(a.getClient())) {
            List<List<ISale>> lista = new ArrayList<>(12);
            for(int i = 0; i<12;i++) {
                List<ISale> s = new ArrayList<>();
                lista.add(s);
            }
            this.filialData.put(a.getClient(),lista);
        }
        List<List<ISale>> monthArr = this.filialData.get(a.getClient());
        List<ISale> saleArr = monthArr.get(a.getMonth()-1);
        saleArr.add(a.clone());

    }

    // da o numero de sales de um cliente num certo mes
    public int getNumberOfSalesPerClientPerMonth(IClient client , int month) {
        return this.filialData.get(client.getCodigo()).get(month).size();
    }

    // da o numero de vendas de um cliente num ano
    public int getNumberOfSalesPerClientPerYear(IClient client){
        int sum = 0;
        for(int i = 0; i<12; i++){
            sum += this.filialData.get(client.getCodigo()).get(i).size();
        }
        return sum;
    }

    // queire estatica
    // faturacao total da filialData
    public double faturacaoTotal(){
        double res = 0;
        for(Map.Entry<String,List<List<ISale>>> lista : this.filialData.entrySet()){
            for(int i = 0; i<12;i++) {
                res += lista.getValue().get(i).stream().mapToDouble(ISale::getPrice).sum();
            }
        }
        return res;
    }

    // querie estatica
    // faturacao total de um dado mes
    public double faturacaoOfMonth(int x){
        if(x > 12){return 0;}
        double res = 0;
        for(Map.Entry<String,List<List<ISale>>> lista : this.filialData.entrySet()){
            res += lista.getValue().get(x-1).stream().mapToDouble(ISale::getPrice).sum();
        }
        return res;
    }


    /**
     * (query2) numero de clintes distintos que compraram num certo mes
     * @param x
     * @return
     */
    public int numberOfClientsBoughtInMonth(int x){
        if(x > 12){return 0;}
        List<ISale> res = new ArrayList<>();
        for(Map.Entry<String,List<List<ISale>>> lista : this.filialData.entrySet()){
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


    /**
     * (query 2) metodo que diz quantas vendas ouve num mes e quantos clintes distintos compraram
     * @param x
     * @return Tuple
     */
    public ITuple<Integer,Integer> totalNumbOfSalesInMonthAndClientsBought(int x){
        if(x > 11 || x < 0){return null;}
        int res = 0;
        Set<String> client = new HashSet<>();
        for(Map.Entry<String,List<List<ISale>>> lista : this.filialData.entrySet()){
            List<ISale> tmp = lista.getValue().get(x);
            res += tmp.size();
            for(ISale sale: tmp ) {
                client.add(sale.getClient());

            }
        }
        return new Tuple<>(res,client.size());
    }

    /**
     * metodo que da o total faturado por um cliente num dado mes
     * @param client
     * @param month
     * @return sum
     */
    public double totalFaturadoPerClientPerMonth(String client,int month) {
        double sum = 0.0;
        List<ISale> tmp = this.filialData.get(client).get(month);
            for(ISale sale : tmp) {
                sum = sale.totalPrice();
            }
        return sum;
    }


    /**
     * (query 3)metodo que determina os produtos distintos comprados por um cliente num dado mes
     *  e o numero de compras
     * @param cliente
     * @param mes
     * @return Tuple
     */
    public ITuple<Integer,Set<String>> numOfDifferentProductsOfClientAndNumOfSales(String cliente,int mes){
        if(mes > 11 || mes < 0){return null;}
        List<ISale> tmp = this.filialData.get(cliente).get(mes);
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


    /**
     * (query 3) metodo que diz quanto gastou um cliente num dado mes
     * @param cliente
     * @param mes
     * @return double
     */
    public double clientSpentInAMonth(String cliente,int mes){
        return this.filialData.get(cliente).get(mes).stream().mapToDouble(ISale::getPrice).sum();
    }


    /**
     * (query 5) lista de codigos dos produtos que comporu por ordem decrescente de quantidade
     * e para quantos iguais por ordem alfabetica
     * @param client
     * @return mapa
     */
    public Map<String,Integer> getListOfProductsBoughtOfClient(String client) {
        Map<String,Integer> mapa = new HashMap<>();
        for(int i = 0; i < 12; i++){
            for(ISale sale :this.filialData.get(client).get(i)){
                if(!mapa.containsKey(sale.getProduct())){
                    mapa.put(sale.getProduct(),sale.getUnits());
                }else {
                    int tmp = mapa.get(sale.getProduct());
                    tmp += sale.getUnits();
                    mapa.put(sale.getProduct(),tmp);
                }
            }
        }

        return mapa;
    }

    /**
     * (query 7)determinar a lista de tres maiores compradores em termos de dinheiro faturado
     * @return Lista com as strings correspondentes ao codigo do cliente
     */
    public List<String> getListOfClientsWhoMostBought(){
        TreeSet<ITuple<String,Double>> tmp = new TreeSet<>(((o1, o2) -> o2.getSecondElem().compareTo(o1.getSecondElem())));
        for(Map.Entry<String,List<List<ISale>>> lista : this.filialData.entrySet()){
            double fact = 0.0;
            for(int i = 0;i<12;i++){
                for(ISale sale :lista.getValue().get(i)){
                    fact += sale.totalPrice();
                }
            }
            tmp.add(new Tuple<>(lista.getKey(),fact));
        }
        List<String> res = new ArrayList<>(3);
        for(int i = 0; i<3;i++) {
            ITuple<String,Double> tuple = tmp.pollFirst();
            if(tuple != null) res.add(tuple.getFirstElem());
        }
        return res;
    }



    /**
     * (query 8)determinar os codigos dos X clientes(sendo X dado pelo utilizador) que compraram
     * mais produtos diferentes(nao interessa a quantidade nem o valor),indicado
     * quantos,sendo o criterio de ordenaçao a ordem decrescente do numero de produtos
     * @return mapa
     */
    public Map<String,Set<String>> getClientsWhoBoughtMostOften(){
        Map<String,Set<String>> mapa = new HashMap<>();
        for(Map.Entry<String,List<List<ISale>>> lista : this.filialData.entrySet()) {
            for(int i = 0; i< 12; i++){
                for(ISale a : lista.getValue().get(i)){
                    if(mapa.containsKey(a.getClient())){
                        mapa.get(a.getClient()).add(a.getProduct());
                    }else {
                        mapa.put(a.getClient(),new HashSet<>());
                        mapa.get(a.getClient()).add(a.getProduct());
                    }
                }
            }
        }
        return mapa;

    }

}
