package com.grupo19.Models;

import com.grupo19.Interfaces.ICatProd;
import com.grupo19.Interfaces.IProduct;
import com.grupo19.Interfaces.ITuple;
import com.grupo19.Tuple;

import java.io.Serializable;
import java.util.*;


public class CatProd implements ICatProd, Serializable {
    private List<Map<String,IProduct>> mapOfProds; //TODO: Tirar

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
           for(Map.Entry<String, IProduct> entry : map.entrySet()) {
               IProduct prod = entry.getValue();
               if(!prod.isProductEverBought()) neverBought.add(prod);
           }
        }
        return neverBought;
    }


    public List<String> productsMostSell (int n) {
        List<ITuple<String,Integer>> tupleList = new ArrayList<>();
        List<String> res = new ArrayList<>(n);
        for(int i=0; i<26;i++) {
            Map<String,IProduct> tmp = this.mapOfProds.get(i);
            for(Map.Entry<String, IProduct> entry : tmp.entrySet()) {
                IProduct tmpProd = entry.getValue();
                tupleList.add(new Tuple<>(tmpProd.getCodigo(),tmpProd.totalOfUnitsBought()));
            }
        }
        tupleList.sort((o1, o2) -> o2.getSecondElem().compareTo(o1.getSecondElem()));
        for(int i=0;i<n;i++) {
            res.add(tupleList.get(i).getFirstElem());
        }
        return res;
    }


    public List<IProduct> listOfProductsThatStartWithLetter (char l) {
        return new ArrayList<>(this.MapOfALetter(l).values());
    }
}
