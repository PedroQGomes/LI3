package com.grupo19.Interfaces;

import java.util.List;

public interface IEstatisticas {

    /**
     * getter para o numero total de vendas invalidas
     * @return  total de vendas invalidas
     */
    int getNumVendasInvalidas();

    /**
     * getter para o numero total de Produtos
     * @return total de produtos
     */
    int getTotalProdNum();

    /**
     * setter para o numero total de vendas
     * @param numVendasTotal inteiro de vendas
     */
    void setNumVendasTotal(int numVendasTotal);

    /**
     * setter para o numero total de produtos
     * @param numProdutosTotal inteiro de produtos
     */
    void setNumProdutosTotal(int numProdutosTotal);

    /**
     * setter para o numero total de clientes
     * @param numClientesTotal inteiro numero de clientes
     */
    void setNumClientesTotal(int numClientesTotal);

    /**
     * setter para o numero total de vendas
     * @param numVendasValidas inteiro numero de vendas
     */
    void setNumVendasValidas(int numVendasValidas);

    /**
     * getter para o numero total de produtos comprados
     * @return total de produtos comprados
     */
    int getNumTotalProdutosComprados();

    /**
     * setter para o numero total de produtos comprados
     * @param numTotalProdutosComprados inteiro numero de produtos
     */
    void setNumTotalProdutosComprados(int numTotalProdutosComprados);

    /**
     * getter para o numero total de clientes que nao realizaram compras
     * @return  numero total de clientes
     */
    int getNumClientesNaoCompraram() ;


    /**
     * setter para o numero total de clientes que nao realizaram compras
     * @param numClientesNaoCompraram inteiro clientes
     */
    void setNumClientesNaoCompraram(int numClientesNaoCompraram);

    /**
     * getter para o numero total de vendas com valor nulo
     * @return  numero total de vendas nulas
     */
    int getNumTotalDeComprasValorNulo() ;


    /**
     * setter para o numero total de vendas com valor nulo
     * @param numTotalDeComprasValorNulo int compras de valor nulo
     */
    void setNumTotalDeComprasValorNulo(int numTotalDeComprasValorNulo);


    /**
     * getter para a faturacao total
     * @return faturacao total
     */
    double getFacturacaoTotal();

    /**
     * setter para a faturacao total
     * @param facturacaoTotal double de facturacao
     */
    void setFacturacaoTotal(double facturacaoTotal);

    /**
     * método toString
     * @return String
     */
    String toString();

    /**
     * getter para os produtos nao comprados
     * @return total de produtos nao comprados
     */
    int getProdNaoComprados();

    /**
     * getter para o numero total de clientes
     * @return total de clientes
     */
    int getTotalClientNum();


    /**
     * calcula facturacao mensal
     * @param factPerMonth double lista de facturacao
     */
    void updateFactPerMonth(double[] factPerMonth);

    /**
     * atualiza as vendas mensais
     * @param numberOfSalesPerMonth lista de numero de vendas mensais
     */
    void updateNumberOfSalesPerMonth(int[] numberOfSalesPerMonth);

    /**
     * atualiza a lista de clientes diferentes que fizeram compras
     * @param diffClientsNumber lista de clientes
     */
    void updateDiffClientsNumber(int[] diffClientsNumber);


}
