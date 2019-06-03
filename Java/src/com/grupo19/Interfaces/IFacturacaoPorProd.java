package com.grupo19.Interfaces;

import java.util.List;

public interface IFacturacaoPorProd {

    void setSalesList(List<ISale> salesAll);
    List<ISale> getSalesList();
    void addSale(ISale s);
    void removeSale(ISale s);
    IFacturacaoPorProd clone();
    double totalSaleProd();
}