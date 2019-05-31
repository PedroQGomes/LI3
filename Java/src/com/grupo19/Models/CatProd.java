package com.grupo19.Models;

import com.grupo19.Interfaces.ICatProd;
import com.grupo19.Interfaces.IProduct;

import java.util.*;

public class CatProd implements ICatProd {
    private Map<String,IProduct> mapOfProds;

    public CatProd() {
        mapOfProds = new HashMap<>();
    }

    public void add (IProduct product) {
        mapOfProds.put(product.getCodigo(),product);
    }


    public boolean contains (String product) {
        return mapOfProds.containsKey(product);
    }


    public void updateProductBought (IProduct product, int filial, int qnt) {
        IProduct prod = mapOfProds.get(product.getCodigo());
        prod.updateProductBought(filial,qnt);
    }

    public List<IProduct> productsNeverBought ( ) {
        return null;
    }


    public List<IProduct> productsMostSell (int n) {
        return null;
    }


    public List<IProduct> listOfProductsThatStartWithLetter (char l) {
        return null;
    }


    public String toString ( ) {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.asList(mapOfProds));
        return sb.toString();
    }
}
