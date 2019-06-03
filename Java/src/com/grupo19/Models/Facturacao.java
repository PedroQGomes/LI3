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
     * @param  umaIFacturacao
     */
    public Facturacao(IFacturacao umaIFacturacao){

        this.arrayOfSales=umaIFacturacao.getArrayOfSales();
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
     * @return
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
     * @param list de Maps
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
     * @param object
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
     * @param ISale sale a adicionar
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
        return new Facturacao(this);
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


    /**
     *
     * @param month inteiro que presenta mes de consulta
     * @param IProduct produto a consultar
     * @return retorna faturacao mensal de um prod(price*units)
     *
     */
    public double totalSalesPerProductPerMonth (int month, IProduct prod) {

        double totalMonth=0;
        Map <String, IFacturacaoPorProd> tmp;
        tmp=arrayOfSales.get(month-1);
        for(Map.Entry<String,IFacturacaoPorProd> entry : tmp.entrySet()) {
            String key = entry.getKey();
            String codProd= prod.getCodigo();
            if(key.equals(codProd)){
                for(ISale s: entry.getValue().getSalesList()){
                    totalMonth+=s.getPrice() * s.getUnits();
                }
            }
        }
        return  totalMonth;
    }

    public List<int> totalUnitsPerProductPerMonth (String codProd) {

        List<int> resultados = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int total = 0;
            Map<String, IFacturacaoPorProd> tmp;
            tmp = arrayOfSales.get(i);
            for (Map.Entry<String, IFacturacaoPorProd> entry : tmp.entrySet()) {
                String key = entry.getKey();
                if (key.equals(codProd)) {
                    for (ISale s : entry.getValue().getSalesList()) {
                        total++;
                    }
                }
            }
            resultados.add(i, total);
        }
        return resultados;
    }

    

}
