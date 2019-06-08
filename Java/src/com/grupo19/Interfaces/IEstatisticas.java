package com.grupo19.Interfaces;

public interface IEstatisticas {

    int getNumVendasInvalidas();
    int getTotalProdNum();
    void setNumVendasTotal(int numVendasTotal);
    void setNumProdutosTotal(int numProdutosTotal);
    void setNumClientesTotal(int numClientesTotal);
    void setNumVendasValidas(int numVendasValidas);
    int getNumTotalProdutosComprados();
    void setNumTotalProdutosComprados(int numTotalProdutosComprados);
    int getNumClientesNaoCompraram() ;
    void setNumClientesNaoCompraram(int numClientesNaoCompraram);
    int getNumTotalDeComprasValorNulo() ;
    void setNumTotalDeComprasValorNulo(int numTotalDeComprasValorNulo);
    double getFacturacaoTotal();
    void setFacturacaoTotal(double facturacaoTotal);
    String toString();
    int getProdNaoComprados();
    int getTotalClientNum();
}
