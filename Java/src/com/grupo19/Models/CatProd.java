package com.grupo19.Models;

import com.grupo19.Interfaces.ICatProd;
import com.grupo19.Interfaces.IProduct;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    public void updateProductBought (String product, int filial, int qnt) {
        IProduct prod = MapOfALetter(product.charAt(0)).get(product);
        prod.updateProductBought(filial-1,qnt);
    }

    public List<IProduct> productsNeverBought ( ) {
        List<IProduct> neverBought = new ArrayList<>();
        for(Map<String,IProduct> map : this.mapOfProds) {
           for(Map.Entry entry : map.entrySet()) {
               IProduct prod = (IProduct) entry.getValue();
               if(!prod.isProductEverBought()) neverBought.add(prod);
           }
        }
        return neverBought;
    }


    public List<IProduct> productsMostSell (int n) {
        return null;
    }


    public List<IProduct> listOfProductsThatStartWithLetter (char l) {
        return new ArrayList<>(this.MapOfALetter(l).values());
    }
}
