package com.grupo19.Interfaces;

import java.util.List;

public interface ICatClient {
    void add(IClient client);
    boolean contains(String codClient);
    void updateClientBought (String client, int filial,String product);
    List<IClient> clientsNeverBought();
    List<IClient> listOfClientsThatBoughtInAllFilials();
    List<IClient> listOfClientsThatDBoughtInAllFilials();
    List<ITuple<String,Integer>> listOfClientsWhoBoughtMost(int n);
}
