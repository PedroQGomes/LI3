package com.grupo19.Models;
import com.grupo19.Interfaces.IClient;

import java.util.Comparator;

public class ComparatorClient implements Comparator<IClient> {

    public int compare(IClient a,IClient b){
        return a.getCodigo().compareTo(b.getCodigo());
    }

}
