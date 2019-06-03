package com.grupo19.Models;

import com.grupo19.Interfaces.ICatProd;
import com.grupo19.Interfaces.IProduct;

import java.util.*;

public class CatProd implements ICatProd {
    private List<Map<String,IProduct>> mapOfProds;

    public CatProd() {
       mapOfProds = new ArrayList<>(26);
       for(int i = 0; i<26; i++) {
           Map<String,IProduct> tmp = new HashMap<>();
           mapOfProds.add(tmp);
       }
    }

    public void add (IProduct product)
    {
        MapOfALetter(product.firstLetter()).put(product.getCodigo(),product.clone());
    }

    private Map<String,IProduct> MapOfALetter(char letter) {
        int index = letter - 'A';
        return mapOfProds.get(index);
    }
    public boolean contains (String product) {
        return MapOfALetter(product.charAt(0)).containsKey(product);
    }


    public void updateProductBought (IProduct product, int filial, int qnt) {
        IProduct prod = MapOfALetter(product.firstLetter()).get(product.getCodigo());
        prod.updateProductBought(filial,qnt);
    }

    public List<IProduct> productsNeverBought ( ) {
        return null;
    }


    public List<IProduct> productsMostSell (int n) {
        return null;
    }


    public List<IProduct> listOfProductsThatStartWithLetter (char l) {
        return new ArrayList<>(this.MapOfALetter(l).values());
    }
}
