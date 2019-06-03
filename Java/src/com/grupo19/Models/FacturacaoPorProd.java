package com.grupo19.Models;

import com.grupo19.Interfaces.IFacturacaoPorProd;
import com.grupo19.Interfaces.ISale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FacturacaoPorProd implements  IFacturacaoPorProd, Serializable {

    /**
     *
     * list de sales de um produto
     *
     */
    private List<ISale> salesList;

    /**
     * Devolve o total das sales do prod (qnt*preco)
     * @return
     */
    public double totalSaleProd ( ) {
        double total=0;
        for(ISale s; salesList){
            total+=s.getUnits() * s.getPrice();
        }
        return  total;
    }

    /**
     *
     * construtor por omissao
     *
     */
    public FacturacaoPorProd(){

        this.salesList= new ArrayList<>();
    }

    /**
     *
     * construtor de cópia
     *
     */
    public FacturacaoPorProd(IFacturacaoPorProd fact){
        this.salesList=getSalesList();
    }

    /**
     *
     * construtor parametrizado
     *
     */
    public  FacturacaoPorProd(List<Sale> list){
       setList(list);

    }


    /**
     *
     * getter da list de sales
     *
     * @return  list de sales
     *
     */

    public List<ISale> getSalesList(){
        return  new ArrayList<> (this.salesList);
        List<String> res = new ArrayList<>();
        for(ISale s: salesList) {
            res.add(s);
        }
        return res;
    }


    /**
     *
     * setter da list de sales
     *
     * @param list de sales
     *
     */

    public void setSalesList(List<ISale> salesAll){
        this.salesList = new ArrayList<> (salesAll);
        salesAll.forEach(s -> {this.salesList.add(s);})
    }

    /**
     *
     * construtor de clone
     *
     * @return clone da IFacturacao
     */
    public IFacturacaoPorProd clone(){
        return new FacturacaoPorProd(this);
    }

    /**
     *
     * adiciona uma ISale a list
     *
     * @param ISale a inserir
     *
     */
    public void  addSale(ISale s){

        salesList.add(s.clone());
    }


    /**
     *
     * remove uma ISale da list, passada como parametro
     *
     * @param ISale a inserir
     *
     */
    public void  removeSale(ISale s){
        salesList.remove(s);
    }



    /**
     *
     * metodo equals
     *
     * */
    public boolean equals(Object obj{
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Facturacao fact=(IFacturacao) obj;
        for(ISale s: fact){
            if(!salesList.contains(s)) return false;
        }
        return true;
    }

}