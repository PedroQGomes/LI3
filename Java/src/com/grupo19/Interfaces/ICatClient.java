package com.grupo19.Interfaces;

import java.util.List;

public interface ICatClient {
    /**
     * adiciona um cliente ao catalogo de clientes
     * @param client
     */
    void add(IClient client);


    /**
     * verifica se um cliente existe no catalogo de clientes
     * @param codClient
     * @return boolean
     */
    boolean contains(String codClient);



    /**
     * atualiza o cliente que comprou um dado produto numa dada filial
     * @param client
     * @param filial
     * @param product
     */
    void updateClientBought (String client, int filial,String product);



    /**
     * dá uma lista com todos os clientes que nunca compraram
     * @return tmp
     */
    List<IClient> clientsNeverBought();



    /**
     * Lista de clientes que compraram em todas as filiais
     * @return lista de clientes que compraram em todas as filiais
     */
    List<IClient> listOfClientsThatBoughtInAllFilials();


    /**
     * Lista de clientes que não compraram em todas as filiais
     * @return lista de clientes que não compraram em todas as filiais
     */
    List<IClient> listOfClientsThatDBoughtInAllFilials();


    /**
     * Lista de X(numero dado) clientes que mais compraram
     * @param n
     * @return res
     */
    List<ITuple<String,Integer>> listOfClientsWhoBoughtMost(int n);
}
