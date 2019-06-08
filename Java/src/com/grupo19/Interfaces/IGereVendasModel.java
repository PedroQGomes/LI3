package com.grupo19.Interfaces;


import java.util.List;
import java.util.Map;


public interface IGereVendasModel {
    /**
     * dá o catalogo de produtos
     * @return IcatProd
     */
    ICatProd getCatProd();


    /**
     * dá as filias
     * @return filiais
     */
    IFilial[] getFiliais();


    /**
     * dá a facturacao
     * @return facturacao
     */
    IFacturacao getFacturacao();


    /**
     * define o nome do ficheiro de vendas
     * @param fichVendas
     */
    void setFichVendas(String fichVendas);


    /**
     * define o tempo que demorou a carregar os dados
     * @param time
     */
    void setTimeOfLoadData(double time);


    /**
     * Metodo para dar resposta a query 2
     *
     * @param x
     * @return tuple de inteiro inteiro
     */
    ITuple<Integer,Integer> totalNumbOfSalesInMonthAndClientsBought(int x, int filial);


    /**
     * dá o nome do ficheiro de vendas
     * @return string
     */
    String getFichVendas();


    /**
     * dá a informacao estatica
     * @return estatisticas
     */
    IEstatisticas getEstatatistica();


    /**
     * dá o tempo que demorou a carregar os dados
     * @return double
     */
    double getTimeOfLoadData();


    /**
     * dá o catalogo de clientes
     * @return ICatClient
     */
    ICatClient getCatClient();


    /**
     * lista de produtos que ninguem comprou
     * @return lista
     */
    List<IProduct> productsNoOneBoughtModel();



    List<IClient> listOfClientsThatBoughtInAllFilials();
    List<IClient> listOfClientsThatDBoughtInAllFilials();


    /**
     * dá a lista de produtos comprados por um cliente
     * @param a
     * @return lista
     */
    List<ITuple<String,Integer>> getListOfProductsBoughtOfClient(String a);


    /**
     * dá a lista de produtos comprados por um cliente
     * @param a
     * @return lista
     */
    List<ITuple<Integer,Integer>> totalPurchasesOfAClientPerYear(String a );


    /**
     * totol faturado por um cliente num dado month
     * @param a
     * @param mes
     * @return double
     */
    double totalFaturadoPClientPMonth(String a,int mes);


    /**
     * dá a lista de cliente que mais compraram
     * @return lista
     */
    List<List<String>> getListOfClientsWhoMostBought();
<<<<<<< HEAD
    List<ITuple<String,Integer>> getClientsWhoBoughtMostOften(int x);
=======


    /**
     * dá os clientes que compraram mais
     * @param x
     * @return lista
     */
    List<ITuple<String,Integer>> getClientsWhoBoughtMostOften(int x);


    /**
     * dá o numero de clientes e a facturaçao
     * @param client
     * @return lista
     */
>>>>>>> 6a8553a20c40b5c0a9fbd223fbdecb20e843e917
    List<List<Double>> getNumClientAndFacturacao(String client);


    /**
     * produtos que mais venderam e o numero dos clientes distintos que o compraram
     * @param n
     * @return lista
     */
    List<ITuple<String,Integer>> productsMostSellAndNumberOfClients(int n);


    /**
     * facturaçao por produto por filial e por mes
     * @param prod
     * @return lista
     */
    List<List<Double>> facturacaoPerProdPerFilialPerMonth(String prod);


    /**
     * salva o estado do programa na app
     * @param fichObject
     */
    void saveState(String fichObject);


    /**
     * da os X clientes que mais compraram um dado produto
     * @param produto
     * @param tamanho
     * @return lista
     */
    List<Map.Entry<String, ITuple<Integer,Double>>> getXClientsWhoMostBoughtProduct(String produto, int tamanho);


    /**
     * atualiza a info estatica
     */
    void updateStaticInfo();

}
