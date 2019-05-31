package com.grupo19.Interfaces;

public interface IProduct {
    String getCodigo();
    boolean isValid();
    IProduct clone();
    void updateProductBought(int filial, int qnt);
}
