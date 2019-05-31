package com.grupo19.Models;

import com.grupo19.Interfaces.IClient;
import com.grupo19.Interfaces.IFilial;
import com.grupo19.Interfaces.ISale;

public class Filial implements IFilial {

    public void add (ISale sale) {

    }


    public IFilial clone ( ) {
        return null;
    }


    public int getNumberOfSalesPerClientPerMonth (IClient client, int month) {
        return 0;
    }


    public int getNumberOfSalesPerClientPerYear (IClient client) {
        int res = 0;
        for(int i = 0; i < 12; i++) {
            res += getNumberOfSalesPerClientPerMonth(client,i+1);
        }
        return res;
    }
}
