package com.grupo19;

import java.util.InputMismatchException;

public class Sale implements  ISale{
    private String codProd, codClient, saleType;
    private int month,filial,units;
    private double price;

    public Sale(String _codProd, double _price ,int _units, String _saleType, String _codClient, int _month, int _filial){
        codProd = _codProd;
        price = _price;
        units = _units;
        saleType = _saleType;
        codClient = _codClient;
        month = _month;
        filial = _filial;
    }

    public static ISale readLineToSale(String line) {
        String codProd, codClient, saleType;
        int month,filial,units;
        double price;
        String[] campos = line.split(" ");
        codProd = campos[0];
        codClient = campos[4];
        saleType = campos[3];
        try {
            price = Double.parseDouble(campos[1]);
            units = Integer.parseInt(campos[2]);
            month = Integer.parseInt(campos[5]);
            filial = Integer.parseInt(campos[6]);
        } catch(InputMismatchException exc) { return null;}
        return new Sale(codProd,price,units,saleType,codClient,month,filial);
    }

}
