package com.grupo19.Interfaces;

public interface IFilial {
    void add(ISale sale);
    IFilial clone();
    int getNumberOfSalesPerClientPerMonth(IClient client , int month);
    int getNumberOfSalesPerClientPerYear(IClient client);
}
