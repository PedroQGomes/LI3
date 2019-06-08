package com.grupo19.Interfaces;


import java.util.List;
import java.util.Map;


public interface IGereVendasModel {
    ICatProd getCatProd();
    IFilial[] getFiliais();
    IFacturacao getFacturacao();
    void setFichVendas(String fichVendas);
    void setTimeOfLoadData(double time);
    ITuple<Integer,Integer> totalNumbOfSalesInMonthAndClientsBought(int x, int filial);
    String getFichVendas();
    IEstatisticas getEstatatistica();
    double getTimeOfLoadData();
    ICatClient getCatClient();
    List<IProduct> productsNoOneBoughtModel();

    List<ITuple<String,Integer>> getListOfProductsBoughtOfClient(String a);
    List<ITuple<Integer,Integer>> totalPurchasesOfAClientPerYear(String a );
    double totalFaturadoPClientPMonth(String a,int mes);
    List<List<String>> getListOfClientsWhoMostBought();

    List<ITuple<String,Integer>> getClientsWhoBoughtMostOften2(int x);
    List<List<Double>> getNumClientAndFacturacao(String client);
    List<ITuple<String,Integer>> productsMostSellAndNumberOfClients(int n);
    List<List<Double>> facturacaoPerProdPerFilialPerMonth(String prod);

    List<Map.Entry<String, ITuple<Integer,Double>>> getXClientsWhoMostBoughtProduct(String produto, int tamanho);
    void updateStaticInfo();

}
