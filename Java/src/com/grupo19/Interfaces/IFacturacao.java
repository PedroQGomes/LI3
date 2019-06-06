package com.grupo19.Interfaces;

import java.util.List;
import java.util.Map;

public interface IFacturacao {
    boolean equals(Object obj);
    void add (ISale sale);
    IFacturacao clone();
    double facturacaoTotal();
    double valorTotalFactMensal (int month);
    double totalSalesPerProductPerMonth (int month, IProduct prod);
    List<Integer> totalUnitsPerProductPerMonth (String codProd);
    int numberOfClientsWhoBought(String codProd);
    List<Integer> numberOfClientsWhoBoughtPerMonth(String codProd);
    List<List<Double>> facturacaoPerProdPerFilialPerMonth(String prod);
    List<Map<String,IFacturacaoPorProd>> getArrayOfSales();
    List<Double> totalSalesPerProduct ( String codProd);
    List<List<Double>> getNumClientAndFacturacao(String client);



}


