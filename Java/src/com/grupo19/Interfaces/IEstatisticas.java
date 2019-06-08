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
     * @param numVendasTotal numero total de vendas
     */
    void setNumVendasTotal(int numVendasTotal);

    /**
     * setter para o numero total de produtos
     * @param numProdutosTotal numero total de produtos
     */
    void setNumProdutosTotal(int numProdutosTotal);

    /**
     * setter para o numero total de clientes
     * @param numClientesTotal numero total de clientes
     */
    void setNumClientesTotal(int numClientesTotal);

    /**
     * setter para o numero total de vendas
     * @param numVendasValidas numero total de vendas validas
     */
    void setNumVendasValidas(int numVendasValidas);

    /**
     * getter para o numero total de produtos comprados
     * @return total de produtos comprados
     */
    int getNumTotalProdutosComprados();

    /**
     * setter para o numero total de produtos comprados
     * @param numTotalProdutosComprados numero total produtos comprados
     */
    void setNumTotalProdutosComprados(int numTotalProdutosComprados);

    /**
     * getter para o numero total de clientes que nao realizaram compras
     * @return  numero total de clientes
     */
    int getNumClientesNaoCompraram() ;


    /**
     * setter para o numero total de clientes que nao realizaram compras
     * @param numClientesNaoCompraram numero total de clientes que nao compraram
     */
    void setNumClientesNaoCompraram(int numClientesNaoCompraram);

    /**
     * getter para o numero total de vendas com valor nulo
     * @return  numero total de vendas nulas
     */
    int getNumTotalDeComprasValorNulo() ;


    /**
     * setter para o numero total de vendas com valor nulo
     * @param numTotalDeComprasValorNulo numero total de comppras de valor nulo
     */
    void setNumTotalDeComprasValorNulo(int numTotalDeComprasValorNulo);


    /**
     * getter para a faturacao total
     * @return faturacao total
     */
    double getFacturacaoTotal();

    /**
     * setter para a faturacao total
     * @param facturacaoTotal faturacao total da app para as x vendas
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

    void updateFactPerMonth(double[] factPerMonth);
    void updateNumberOfSalesPerMonth(int[] numberOfSalesPerMonth);
    void updateDiffClientsNumber(int[] diffClientsNumber);
    List<double[]> getFactPerMonth();
    List<int[]> getNumberOfSalesPerMonth();
    List<int[]> getDiffClientsNumber();


}
