package com.grupo19.Interfaces;

import java.util.List;

public interface ICatProd {
    void add(IProduct product);
    void contains(IProduct product);
    List<IProduct> productsNeverBought();
    List<IProduct> productsMostSell(int n);
    List<IProduct> listOfProductsThatStartWithLetter(char l);
}
