package com.grupo19.Interfaces;

import com.grupo19.Tuple;

import java.util.List;

public interface IGereVendasModel {
    ICatProd getCatProd();
    IFilial[] getFiliais();
    IFacturacao getFacturacao();
    void setFichVendas(String fichVendas);
    void setTimeOfLoadData(double time);
    Tuple<Integer,Integer> totalNumbOfSalesInMonthAndClientsBought(int x, int filial);
    String getFichVendas();
    IEstatisticas getEstatatistica();
    double getTimeOfLoadData();
    ICatClient getCatClient();
    List<IProduct> listOfProductsWithLetter(char letter);
    List<IProduct> productsNoOneBoughtModel();
    List<IClient> listOfClientsThatBoughtInAllFilials();
    List<IClient> listOfClientsThatDBoughtInAllFilials();
    List<Tuple<Integer,Integer>> totalPurchasesOfAClientPerYear(String client);
    double totalFaturadoPClientPMonth(String client,int month);

}
