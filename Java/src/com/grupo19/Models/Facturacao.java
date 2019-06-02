package com.grupo19.Models;

import com.grupo19.Interfaces.IFacturacao;


public class Facturacao implements IFacturacao {

    /** colecao que associa mes(int) a uma list de sales com preco P */
    private Map<int,List<Sale>> fatMensalP;

    /** colecao que associa mes(int) a uma list de sales com preco P */
    private Map<int,List<Sale>> fatMensalN;

    /** list de sales com preco P*/
    private List<Sale> salesListP;

    /** list de sales com preco N*/
    private List<Sale> salesListN;
    







    /** construtor por omissao */
    public Facturacao(){
        this.fatMensalP=getFatMensalP();
        this.fatMensalN=getFatMensalN();
        this.salesListP=getSaleListP();
        this.salesListN=getSaleListN();
    }

    /** construtor de cópia */
    public Facturacao(Facturacao umaFacturacao){
        this.fatMensalP=umaFacturacao.getFatMensalP();
        this.fatMensalN=umaFacturacao.getFatMensalN();
        this.salesListP=umaFacturacao.getSaleListP();
        this.salesListN=umaFacturacao.getSaleListN();
    }

    /** construtor parametrizado */
    public  Facturacao(Map<int,List<Sale>> mensalN, Map<int,List<Sale>> mensalP, List<Sale> salesN, List<Sale> salesP){
        this.fatMensalP=mensalP;
        this.fatMensalN=mensalN;
        this.salesListP=salesP;
        this.salesListN=salesN;
    }

    /** getter da colecao de sales com preco P*/
    public Map<int, List<Sale>> getFatMensalP() {
        return this.fatMensalP.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    /** setter da colecao de sales com preco P*/
    public void setFatMensalP(Map<int, List<Sale>> fatMensalP) {
        this.fatMensalP=fatMensalP.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    /** getter da colecao de sales com preco N*/
    public Map<int, List<Sale>> getFatMensalN() {
        return this.fatMensalN.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    /** setter da colecao de sales com preco N*/
    public void setFatMensalN(Map<int, List<Sale>> fatMensalN) {
        this.fatMensalN=fatMensalN.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    /** getter da list de sales P */
    public List<Sale> getSalesListP(){
        ArrayList novo = new ArrayList<Sale> (this.salesListP.size());
        for ( Sale s : this.salesListP )
        {
            novo.add(s.clone());
        }
        return novo;
    }

    /** setter da list de sales P */
    @param list de sales com precos N e P
    public void setSalesListP(List<Sale> salesAll){
        this.salesListP= new ArrayList<List> (salesAll.size());
        for ( Sale s: salesAll)
        {
            if(s.getSaleType().equals("P")) salesListP.add(s.clone());
        }
    }

    /** getter da list de sales N*/
    public List<Sale> getSalesListN(){
        ArrayList novo = new ArrayList<Sale> (this.salesListN.size());
        for ( Sale s : this.salesListN )
        {
            novo.add(s.clone());
        }
        return novo;
    }


    /** setter da list de sales N*/
    @param list de sales com precos N e P
    public void setSalesListN(List<Sale> salesAll){
        this.salesListN= new ArrayList<List> (salesAll.size());
        for ( Sale s: salesAll)
        {
           if(s.getSaleType().equals("N")) salesListN.add(s.clone());
        }
    }

    /** método clone */
    public boolean equals(Object obj){
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Facturacao fact=(Facturacao) obj;
        return fact.getFactMensalP().equals(fatMensalP) && fact.getFatMensalN().equals(fatMensalN)
                && fact.getSalesListP().equals(salesListP) && fact.getSalesListN().equals(salesListN);
    }
}
