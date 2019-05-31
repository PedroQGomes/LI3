package com.grupo19.Interfaces;

import com.grupo19.Models.Client;
import java.util.Set;

public interface ICatClient {
    void addClient(Client a);
    Set<IClient> getSetClient();
    
}
