package com.grupo19.Interfaces;

public interface IClient {
    String getCodigo();
    boolean isValid();
    void updateClientBought(int filial);
    IClient clone();
    boolean hasClientEverBought ();
}
