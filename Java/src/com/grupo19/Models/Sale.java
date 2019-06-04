package com.grupo19.Models;

import com.grupo19.GereVendasModel;
import com.grupo19.Interfaces.ICatClient;
import com.grupo19.Interfaces.ICatProd;
import com.grupo19.Interfaces.ISale;

import java.util.InputMismatchException;

public class Sale implements ISale {

    private String prod, client, saleType;
    private int month,filial,units;
    private double price;

    public String getProduct ( ) {
        return prod;
    }

    public String getClient ( ) {
        return client;
    }


    public boolean isValid(ICatProd iCatProd , ICatClient iCatClient) {
        return (iCatProd.contains(getProduct()) &&
                iCatClient.contains(getClient()) &&
                getMonth() > 0 && getMonth() < 13 &&
                getUnits() > 0 && getUnits() < 1000 &&
                getFilial() > 0 && getFilial() < (GereVendasModel.getNumFiliais()+1) &&
                (getSaleType().equals("N") || getSaleType().equals("P")));

    }

    public Sale(String _codProd, double _price ,int _units, String _saleType, String _codClient, int _month, int _filial){
        prod = _codProd;
        price = _price;
        units = _units;
        saleType = _saleType;
        client = _codClient;
        month = _month;
        filial = _filial;
    }

    public Sale(Sale s){
        this.prod = s.getCodProd();
        this.client = s.getCodClient();
        this.filial = s.getFilial();
        this.month = s.getMonth();
        this.price = s.getPrice();
        this.units = s.getUnits();
        this.saleType = s.getSaleType();
    }


    public String getCodProd() {
        return prod;
    }

    public String getCodClient() {
        return client;
    }

    public String getSaleType() {
        return saleType;
    }

    public int getMonth() {
        return month;
    }

    public int getFilial() {
        return filial;
    }

    public int getUnits() {
        return units;
    }

    public double getPrice() {
        return price;
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

    @Override
    public boolean equals (Object o) {
        if(o == this) return true;
        if(!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return (sale.getProduct().equals(this.getProduct()) &&
                sale.getClient().equals(this.getClient()) &&
                sale.getPrice() == this.getPrice() &&
                sale.getFilial() == this.getFilial() &&
                sale.getMonth() == this.getFilial() &&
                sale.getSaleType().equals(this.getSaleType()) &&
                sale.getUnits() == this.getUnits());
    }

    public ISale clone ( ) { //TODO : FAZER CLONE
       return  new Sale(this);
    }

}
