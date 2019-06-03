package com.grupo19.Models;
//package com.grupo19.Interfaces;
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
     * @param umaFacturacao
     */
    public Facturacao(IFacturacao umaIFacturacao){

        this.arrayOfSales=umaIFacturacao.getArrayOfSales();
    }

    /**
     * Construtor parametrizado
     * @param arrayS
     */
    public  Facturacao(List<Map<String,IFacturacaoPorProd>> arrayS){

        setSArrayOfSales(arrayS);
    }


    /**
     * Getter da lista de Sales
     * @return
     */
    public List<Map<String,IFacturacaoPorProd>> getArrayOfSales(){
        List<Map<String, IFacturacaoPorProd>> nova = new ArrayList<>();
        for(int i=0;i<12;i++){
            Map <String, IFacturacaoPorProd> tmp = new TreeMap<>(String::compareTo);
            tmp=arrayOfSales.get(i).clone();
            nova.add(i,tmp);
        }
        return nova;
    }

    /**
     *
     * setter da list de ISales
     * @param List de Maps
     *
    */
    public void setSArrayOfSales(List<Map<String,IFacturacaoPorProd>> salesAll){
        this.arrayOfSales= new ArrayList<>(salesAll);
        for(int i=0;i<12;i++){
            Map <String, IFacturacaoPorProd> tmp = new TreeMap<>(String::compareTo);
            tmp=salesAll.get(i).clone();
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
        //return fact.getArrayOfSales().equals(arrayOfSales);
    }

    /**
     *
     * adiciona uma ISale à facturacao
     * @param Isale sale a adicionar
     *
     * */
    public void add (ISale sale) {
        int month=sale.getMonth() -1;
        String codprod=sale.getProduct();
        IFacturacaoPorProd value = arrayOfSales.get(month).get(codprod);            // ˇˇˇˇˇˇˇˇˇˇˇˇˇˇ
        value.addSale(sale.clone());
        //arrayOfSales.get(month).put(codprod,IFacturacaoPorProd.add(sale));

    }
    /**
     *
     * metodo de clone
     *
     */
    public IFacturacao clone() {
        List<Map<String, IFacturacaoPorProd>> nova = new ArrayList<>();
            for(int i=0; i<12; i++) {
                Map <String, IFacturacaoPorProd> tmp = new TreeMap<>(String::compareTo);
                tmp=arrayOfSales.get(month-1).clone();
                nova.put(tmp);
            }
        return nova;
    }


    /**
     *
     * Faturacao mensal total
     * @param mes de consulta
     * @return total faturado
     *
     * */

    public double valorTotalFactMensal (int month) {
        double total=0;
        Map <String, IFacturacaoPorProd> tmp = new TreeMap<>(String::compareTo);
        tmp=arrayOfSales.get(month-1).clone();
        for(Map.Entry<String,IFacturacaoPorProd> tmp : treeMap.entrySet()) {
            String key = tmp.getKey();
            IFacturacaoPorProd arrayMonth = tmp.getValue();
            for(Isale s: arrayMonth){
                total+=s.getPrice() * s.getUnits();
            }
        }
        return total;
    }


    /**
     *
     * @param month inteiro que presenta mes de consulta
     * @param IProduct produto a consultar
     * @return retorna faturacao mensal de um prod(price*units)
     *
     */
    public double totalSalesPerProductPerMonth (int month, IProduct prod) {

        double totalMonth=0;
        Map <String, IFacturacaoPorProd> tmp = new TreeMap<>(String::compareTo);
        tmp=arrayOfSales.get(month-1).clone();
        for(Map.Entry<String,IFacturacaoPorProd> tmp : treeMap.entrySet()) {
            String key = tmp.getKey();
            String codProd= prod.getCodigo();
            if(key.equals(codProd)){
                for(Isale s: arrayMonth){
                    totalMonth+=s.getPrice() * s.getUnits();
                }
            }
        }
        return  totalMonth;
    }


}
