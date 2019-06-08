package com.grupo19.Interfaces;

public interface IClient {
    String getCodigo();
    boolean isValid();
    IClient clone();
    int NumDiffProductsBought();
    void updateClientBought (int filial,String product);
    boolean hasClientEverBought ();
}
