package com.grupo19.Interfaces;

import java.util.List;
import java.util.Map;

public interface IFilial {
    void add(ISale sale);
    IFilial clone();
    int getNumberOfSalesPerClientPerMonth(IClient client , int month);
    int getNumberOfSalesPerClientPerYear(IClient client);
    Map<String,List<List<ISale>>> getFilial();
    Map<String,Integer> getListOfProductsBoughtOfClient(String client);
}
