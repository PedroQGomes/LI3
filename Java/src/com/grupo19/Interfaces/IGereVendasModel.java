package com.grupo19.Interfaces;

import java.util.List;

public interface IGereVendasModel {
    void loadData();
    ICatProd getCatProd();
    ICatClient getCatClient();
    List<IProduct> listOfProductsWithLetter(char letter);
    List<IProduct> productsNoOneBoughtModel();
    List<IClient> listOfClientsThatBoughtInAllFilials();
    List<IClient> listOfClientsThatDBoughtInAllFilials();
}
