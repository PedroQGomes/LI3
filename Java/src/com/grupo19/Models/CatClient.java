package com.grupo19.Models;

import com.grupo19.Interfaces.ICatClient;
import com.grupo19.Interfaces.IClient;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CatClient implements ICatClient {
    private Set<IClient> catalogo;

    public CatClient() {
        this.catalogo = new TreeSet<IClient>(new ComparatorClient());
    }

    public void addClient(Client a){
        this.catalogo.add(a.clone());
    }

    public Set<IClient> getSetClient(){
        return this.catalogo.stream().map(IClient::clone).collect(Collectors.toSet());
    }
}
