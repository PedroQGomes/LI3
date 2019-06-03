package com.grupo19.Interfaces;

import java.util.List;
import java.util.Map;

public interface IFacturacao {

    void add(ISale sale);

    //Facturacao initFacturacao(list<Sale>);
    IFacturacao clone();
    //Facturacao (Facturacao facturacao);
   // Map<int,List<Sale>> totalProdMensal();
   // Map<int,List<Sale>> totalFactMensal();
    //boolean contains(Facturacao fact,);
   // Map<int,List<Sale> > factPorMesClient(Client client);
    double valorTotalFactMensal(int month);
    List<Map<String,IFacturacaoPorProd>> getArrayOfSales();
    //double valorTotalProdMensal(int month);
    int totalSalesPerProductPerMonth(int month, IProduct prod);
}


