package com.grupo19.Models;

import com.grupo19.Interfaces.IFacturacao;


public class Facturacao implements IFacturacao, serializable {

    /** list de maps de Isales */
    private list<Map<String,FacturacaoPorProd>> arrayOfSales;



    /** construtor por omissao */
    public Facturacao(){
        this.arrayOfSales=getArrayOfSales();

    }

    /** construtor de cópia */
    public Facturacao(Facturacao umaFacturacao){
        this.arrayOfSales=umaFacturacao.getarrayOfSales();
    }

    /** construtor parametrizado */
    public  Facturacao(list<Map<String,FacturacaoPorProd>> arrayS){
        this.arrayOfSales=arrayS;
    }



    /** getter da list de Isales*/
    public List<Map<String,FacturacaoPorProd>> getArrayOfSales(){
        ArrayList novo = new ArrayList<Map<String,FacturacaoPorProd>> (this.arrayOfSales.size());
        for(Map mp:arrayOfSales){
            novo.add(mp.clone());
        }
        return novo;
    }

    /** setter da list de sales P */
    @param list de sales com precos N e P
    public void setSArrayOfSales(List<Map<String,FacturacaoPorProd>> salesAll){
        this.arrayOfSales= new ArrayList<Map<String,FacturacaoPorProd>> (salesAll.size());
        for(Map mp:arrayOfSales){
            novo.add(mp.clone());
        }
        return novo;

    }

    
    /** método clone */
    public boolean equals(Object obj){
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        IFacturacao fact=(IFacturacao) obj;
        return fact.getArrayOfSales().equals(arrayOfSales);
    }



}
