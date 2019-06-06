package com.grupo19.Interfaces;

import java.util.List;

public interface ICatProd {
    void add(IProduct product);
    List<String> productsMostSell (int n);
    boolean contains(String codProd);
    void updateProductBought(String codProd , int filial , int qnt);
    List<IProduct> productsNeverBought();
    List<IProduct> listOfProductsThatStartWithLetter(char l);
}
