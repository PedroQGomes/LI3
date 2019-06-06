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

    public List<Integer> numberOfClientsWhoBought(String codProd);

    List<Double> totalSalesPerProduct ( String codProd);
    List<List<Double>> getMumClientAndFacturacao(String client);



}


