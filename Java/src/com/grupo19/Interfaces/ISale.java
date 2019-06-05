package com.grupo19.Interfaces;

public interface ISale {
    String getProduct();
    String getClient();
    String getSaleType();
    int getMonth();
    int getFilial();
    int getUnits();
    double getPrice();
    double totalPrice();
    boolean isValid(ICatProd iCatProd , ICatClient iCatClient);
    ISale clone();
}
