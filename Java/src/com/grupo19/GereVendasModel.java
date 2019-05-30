package com.grupo19;

import com.grupo19.Interfaces.IGereVendasModel;

public class GereVendasModel implements IGereVendasModel {
    private static final int NUM_FILIAIS = 3;

    @Override
    public void loadData ( ) {

    }


    public static int getNumFiliais ( ) {
        return NUM_FILIAIS;
    }
}
