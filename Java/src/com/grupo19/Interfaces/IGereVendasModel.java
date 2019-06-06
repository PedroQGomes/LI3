package com.grupo19.Interfaces;

import com.grupo19.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    List<String> getListOfProductsBoughtOfClient(String a);
    List<Tuple<Integer,Integer>> totalPurchasesOfAClientPerYear(String a );
    double totalFaturadoPClientPMonth(String a,int mes);
    List<List<String>> getListOfClientsWhoMostBought();
    List<Map.Entry<String, Set<String>>> getClientsHowBoughtMostOften(int x);

}
