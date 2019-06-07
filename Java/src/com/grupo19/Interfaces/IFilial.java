package com.grupo19.Interfaces;

import com.grupo19.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFilial {
    void add(ISale sale);
    IFilial clone();
    int getNumberOfSalesPerClientPerMonth(IClient client , int month);
    int getNumberOfSalesPerClientPerYear(IClient client);
    double totalFaturadoPerClientPerMonth(String client,int month);
    ITuple<Integer, Set<String>> numOfDifferentProductsOfClientAndNumOfSales(String cliente, int mes);
    ITuple<Integer,Integer> totalNumbOfSalesInMonthAndClientsBought(int x);
    Map<String,List<List<ISale>>> getFilialData();
    Map<String,Integer> getListOfProductsBoughtOfClient(String client);
    List<String> getListOfClientsWhoMostBought();
    Map<String,Set<String>> getClientsWhoBoughtMostOften();
}
