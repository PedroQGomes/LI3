package com.grupo19.Interfaces;

import java.util.List;

public interface ICatProd {
    void add(IProduct product);
    boolean contains(String codProd);
    void updateProductBought(String codProd , int filial , int qnt);
    List<IProduct> productsNeverBought();
    List<IProduct> productsMostSell(int n);
    List<IProduct> listOfProductsThatStartWithLetter(char l);
}
