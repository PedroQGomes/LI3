package com.grupo19.Models;

import com.grupo19.Interfaces.IFacturacao;
import com.grupo19.Interfaces.IFacturacaoPorProd;
import com.grupo19.Interfaces.IProduct;
import com.grupo19.Interfaces.ISale;

import java.io.Serializable;
import java.util.*;


public class Facturacao implements IFacturacao, Serializable {


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
        this.arrayOfSales=new ArrayList<>(arrayS);
    }


    /**
     * Getter da lista de Sales
     * @return
     */
    public List<Map<String,IFacturacaoPorProd>> getArrayOfSales(){
        return new ArrayList<>(this.arrayOfSales);
    }

    /**
     *
     * setter da list de ISales
     * @param List de Maps
     *
    */
    public void setSArrayOfSales(List<Map<String,IFacturacaoPorProd>> salesAll){
        this.arrayOfSales= new ArrayList<>(salesAll);
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
        return fact.getArrayOfSales().equals(arrayOfSales);
    }


    public void add (ISale sale) {

    }

    public IFacturacao clone ( ) {
        return null;
    }

    public double valorTotalFactMensal (int month) {
        return 0;
    }

    public int totalSalesPerProductPerMonth (int month, IProduct prod) {
        return 0;
    }

    /** Não é necessario
     * adicionar um map na posicao do month
     *
     * @param Map a inserir
     * @param month para identifcar o index do map
     *

    public void addMap(Map<String,IFacturacaoPorProd> mapToADD, int month){
        arrayOfSales.remove(month-1);
        arrayOfSales.set(month-1,mapToADD);
    } */



}
