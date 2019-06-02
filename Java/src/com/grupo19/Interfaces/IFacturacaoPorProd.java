package com.grupo19.Interfaces;

public interface IFacturacaoPorProd {

    public void setSalesList(List<ISale> salesAll);
    public List<ISale> getSalesList();
    public List<ISale> addSale(ISale s);
    public List<ISale> removeSale(ISale s);

}