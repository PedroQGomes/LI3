package com.grupo19.Interfaces;

public interface IProduct {
    String getCodigo();
    boolean isValid();
    IProduct clone();
    char firstLetter();
    void updateProductBought(int filial, int qnt);
    boolean isProductEverBought();
}
