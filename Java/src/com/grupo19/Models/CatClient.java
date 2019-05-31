package com.grupo19.Models;

import com.grupo19.Interfaces.ICatClient;
import com.grupo19.Interfaces.IClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatClient implements ICatClient {
    private Map<String,IClient> mapOfClients;

    public CatClient() {
        mapOfClients = new HashMap<>();
    }
    public void add (IClient product) {
        mapOfClients.put(product.getCodigo(),product);
    }


    public boolean contains (String codClient) {
        return mapOfClients.containsKey(codClient);
    }


    public void updateClientBought (IClient client, int filial) {
        IClient clt = mapOfClients.get(client.getCodigo());
        clt.updateClientBought(filial);
    }

    public List<IClient> clientsNeverBought ( ) {
        return null;
    }

    public List<IClient> clientsMostBought (int n) {
        return null;
    }

    public List<IClient> listOfClientsThatStartWithLetter (char l) {
        return null;
    }

    public List<IClient> listOfClientsThatBoughtInAllFilials ( ) {
        return null;
    }

    public String toString ( ) {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.asList(mapOfClients));
        return sb.toString();
    }
}
