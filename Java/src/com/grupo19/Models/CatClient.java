package com.grupo19.Models;

import com.grupo19.Interfaces.ICatClient;
import com.grupo19.Interfaces.IClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatClient implements ICatClient {
    private Map<String,IClient> mapOfClients; //TODO : ORGANIZAR POR LETRA

    public CatClient() {
        mapOfClients = new HashMap<>();
    }
    public void add (IClient client) {
        mapOfClients.put(client.getCodigo(),client);
    }


    public boolean contains (String codClient) {
        return mapOfClients.containsKey(codClient);
    }


    public void updateClientBought (String client, int filial) {
        IClient clt = mapOfClients.get(client);
        clt.updateClientBought(filial-1);
    }

    public List<IClient> clientsNeverBought ( ) {
        List<IClient> tmp = new ArrayList<>();
        for(Map.Entry entry: mapOfClients.entrySet()) {
            IClient tmpClient = (IClient) entry.getValue();
            if(!tmpClient.hasClientEverBought()) tmp.add(tmpClient.clone());
        }
        return tmp;
    }


    /**
     * Lista de clientes que não compraram em todas as filiais
     * @return
     */

    public List<IClient> listOfClientsThatDBoughtInAllFilials ( ) {
        return this.mapOfClients.values().stream().filter(c->!c.hasClientEverBought()).collect(Collectors.toList());
    }

    /**
     * Lista de clientes que compraram em todas as filiais
     * @return
     */
    public List<IClient> listOfClientsThatBoughtInAllFilials ( ) {
        return this.mapOfClients.values().stream().filter(IClient::hasClientEverBought).collect(Collectors.toList());
    }

}
