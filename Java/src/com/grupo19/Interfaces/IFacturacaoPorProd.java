package com.grupo19.Interfaces;

import java.util.List;

public interface IFacturacaoPorProd {


    List<ISale> getSalesList();
    void addSale(ISale s);
    IFacturacaoPorProd clone();
    double totalSaleProd();
    double getDifClientsWhoBought();
    List<Double> factPerFilial();
}