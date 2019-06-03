package com.grupo19.Models;

import com.grupo19.Interfaces.IFacturacao;
import com.grupo19.Interfaces.IFacturacaoPorProd;
import com.grupo19.Interfaces.IProduct;
import com.grupo19.Interfaces.ISale;

import java.io.Serializable;
import java.util.*;


public class Facturacao implements IFacturacao, IFacturacaoPorProd Serializable {

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
    public Facturacao(IFacturacao umaFacturacao){

        this.arrayOfSales=umaFacturacao.getArrayOfSales();
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


    public void add (ISale sale) {
        int month=sale.getMonth() -1;
        String codprod=sale.getCodProd();
        arrayOfSales.get(month).put(codprod,FacturacaoPorProd.add(sale)); // posso fazer isto?

    }

    public IFacturacao clone ( ) {

        return new (IFacturacao(this));
    }

    public double valorTotalFactMensal (int month) {
        
        return 0;
    }

    public int totalSalesPerProductPerMonth (int month, IProduct prod) {
        return 0;
    }


}
