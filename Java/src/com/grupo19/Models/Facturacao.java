package com.grupo19.Models;

import com.grupo19.Interfaces.IFacturacao;
import com.grupo19.Interfaces.IFacturacaoPorProd;
import com.grupo19.Interfaces.IProduct;
import com.grupo19.Interfaces.ISale;

import java.io.Serializable;
import java.util.*;


public class Facturacao implements IFacturacao, Serializable {

    /**
     *
     * string é codProd
     * IFacturacaoPorProd é uma list de sales de um produto num mes
     *
     * */
    private List<Map<String, IFacturacaoPorProd>> arrayOfSales;


    /**
     * Construtor por omissão
     */
    public Facturacao(){
        this.arrayOfSales= new ArrayList<>();
        for(int i = 0; i<12 ; i++){
            Map <String, IFacturacaoPorProd> tmp = new TreeMap<>(String::compareTo);
            arrayOfSales.add(tmp);
        }

    }

    /**
     * Construtor de cópia
     * @param  umaFacturacao
     */
    public Facturacao(Facturacao umaFacturacao){

        this.arrayOfSales=umaFacturacao.getArrayOfSales();
    }

    /**
     * Construtor parametrizado
     * @param arrayS
     */
    public  Facturacao (List<Map<String,IFacturacaoPorProd>> arrayS){

        setSArrayOfSales(arrayS);
    }


    /**
     * Getter da lista de Sales
     * @return nova list de sales
     */
    public List<Map<String,IFacturacaoPorProd>> getArrayOfSales(){
        List<Map<String, IFacturacaoPorProd>> nova = new ArrayList<>();
        for(int i=0;i<12;i++){
            Map <String, IFacturacaoPorProd> tmp;
            tmp=arrayOfSales.get(i);
            nova.add(i,tmp);
        }
        return nova;
    }

    /**
     *
     * setter da list de ISales
     * @param salesAll list de Maps
     *
    */
    public void setSArrayOfSales(List<Map<String,IFacturacaoPorProd>> salesAll){
        this.arrayOfSales= new ArrayList<>(salesAll);
        for(int i=0;i<12;i++){
            Map <String, IFacturacaoPorProd> tmp;
            tmp=salesAll.get(i);
            arrayOfSales.add(i,tmp);
        }

    }


    /**
     * Equals
     * @param obj
     * @return
     */
    public boolean equals(Object obj){ // TODO: ESTE EQUALS , MELHORAR
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Facturacao fact=(Facturacao) obj;
        return false;
        //return fact.getArrayOfSales().equals(arrayOfSales);
    }

    /**
     *
     * adiciona uma ISale à facturacao
     * @param sale sale a adicionar
     *
     * */
    public void add (ISale sale) {
        int month=sale.getMonth() -1;
        String codprod =sale.getProduct();
        IFacturacaoPorProd value = arrayOfSales.get(month).get(codprod);
        if(value == null) {
            value = new FacturacaoPorProd();
            Map<String,IFacturacaoPorProd> mapTmp = arrayOfSales.get(month);
            mapTmp.put(codprod,value);
        }
        value.addSale(sale.clone());

    }
    /**
     *
     * metodo de clone
     *
     */
    public IFacturacao clone() {

        return new Facturacao(this);
    }


    /**
     *
     * Faturacao mensal total
     * @param month de consulta
     * @return total faturado
     *
     * */

    public double valorTotalFactMensal (int month) {
        double total=0;
        Map <String, IFacturacaoPorProd> tmp;
        tmp=arrayOfSales.get(month-1);
        for(Map.Entry<String,IFacturacaoPorProd> entry : tmp.entrySet()) {
            IFacturacaoPorProd arrayMonth = entry.getValue();
            for(ISale s: arrayMonth.getSalesList()){
                total+=s.getPrice() * s.getUnits();
            }
        }
        return total;
    }

    public double facturacaoTotal() {
        double total = 0.0;
        for(int i = 1; i<=12 ; i++) {
            total += this.valorTotalFactMensal(i);
        }
        return total;
    }


    /**
     *
     * @param month inteiro que presenta mes de consulta
     * @param prod produto a consultar
     * @return retorna faturacao mensal de um prod(price*units)
     *
     */
    public double totalSalesPerProductPerMonth (int month, IProduct prod) {

        double totalMonth=0;
        Map <String, IFacturacaoPorProd> tmp;
        tmp=arrayOfSales.get(month-1);
        for(Map.Entry<String,IFacturacaoPorProd> entry : tmp.entrySet()) {
            String key = entry.getKey();
            String codProd = prod.getCodigo();
            if(key.equals(codProd)){
                for(ISale s: entry.getValue().getSalesList()){
                    totalMonth += s.getPrice() * s.getUnits();
                }
            }
        }
        return  totalMonth;
    }




    /**
     *
     * Calcula o numero de unidades vendidas de um dado produto, devolvendo numa list o total por mes
     * @param codProd
     * @return resultados
     *
     */
    public List<Integer> totalUnitsPerProductPerMonth (String codProd) {

        List<Integer> resultados = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int total = 0;
            Map<String, IFacturacaoPorProd> tmp;
            tmp = arrayOfSales.get(i);
            for (Map.Entry<String, IFacturacaoPorProd> entry : tmp.entrySet()) {
                String key = entry.getKey();
                if (key.equals(codProd)) {
                    for (ISale s : entry.getValue().getSalesList()) {
                        total += s.getUnits();
                    }
                }
            }
            resultados.add(i, total);
        }
        return resultados;
    }



    /**
     *
     * Calcula a list por mes, de clientes que compraram o produto passado como parametro
     *
     * @param codProd
     * @return resultados
     *
     */

    public List<Integer> numberOfClientsWhoBought(String codProd) { //TODO: MELHORAR QUERY USAR HASHSET
        List<Integer> resultados = new ArrayList<>();

        for(int i=0;i<12;i++){
            int total=0;
            HashSet<String> clients = new HashSet<String>();
            Map <String, IFacturacaoPorProd> tmp;
            tmp=arrayOfSales.get(i);
            for(ISale s: tmp.get(codProd).getSalesList()){
                clients.add(s.getClient());
            }
            total=clients.size();
            resultados.add(i,total);
        }
        return resultados;
    }


    /**
     *
     * valor total mensal, de um dado produto(price*units)
     *
     * @param codProd
     * @return res
     *
     */
    public List<Double> totalSalesPerProduct ( String codProd){
        List<Double> res= new ArrayList<>();
        for(int i=0;i<12;i++){
            double totalMonth=0;
            Map <String, IFacturacaoPorProd> tmp;
            tmp=arrayOfSales.get(i);
            for(Map.Entry<String,IFacturacaoPorProd> entry : tmp.entrySet()) {
                String key = entry.getKey();
                if(key.equals(codProd)){
                    for(ISale s: entry.getValue().getSalesList()){
                        totalMonth+=s.getPrice() * s.getUnits();
                    }
                }
            }
         res.add(i,totalMonth);
        }
        return  res;
    }




}
