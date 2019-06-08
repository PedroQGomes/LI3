package com.grupo19.Models;


import com.grupo19.Interfaces.IEstatisticas;

import java.io.Serializable;

public class Estatistica implements IEstatisticas, Serializable {

    private int numVendasValidas;
    private int numVendasTotal;
    private int numProdutosTotal;
    private int numClientesTotal;
    private int numTotalProdutosComprados;
    private int numClientesNaoCompraram;
    private int numTotalDeComprasValorNulo;
    private double facturacaoTotal;
    
    public int getNumProdutosTotal() {
        return numProdutosTotal;
    }

    public void setNumProdutosTotal(int numProdutosTotal) {
        this.numProdutosTotal = numProdutosTotal;
    }

    public void setNumClientesTotal(int numClientesTotal) {
        this.numClientesTotal = numClientesTotal;
    }

    public void setNumVendasTotal(int numVendasTotal) {
        this.numVendasTotal = numVendasTotal;
    }

    public void setNumVendasValidas(int numVendasValidas) {
        this.numVendasValidas = numVendasValidas;
    }


    public int getNumVendasInvalidas() {
        return this.numVendasTotal-this.numVendasValidas;
    }


    public int getTotalProdNum() {
        return this.numProdutosTotal;
    }

    public int getNumTotalProdutosComprados() {
        return numTotalProdutosComprados;
    }

    public void setNumTotalProdutosComprados(int numTotalProdutosComprados) {
        this.numTotalProdutosComprados = numTotalProdutosComprados;
    }

    public int getNumClientesNaoCompraram() {
        return numClientesNaoCompraram;
    }

    public void setNumClientesNaoCompraram(int numClientesNaoCompraram) {
        this.numClientesNaoCompraram = numClientesNaoCompraram;
    }

    public int getNumTotalDeComprasValorNulo() {
        return numTotalDeComprasValorNulo;
    }

    public void setNumTotalDeComprasValorNulo(int numTotalDeComprasValorNulo) {
        this.numTotalDeComprasValorNulo = numTotalDeComprasValorNulo;
    }

    public double getFacturacaoTotal() {
        return facturacaoTotal;
    }

    public void setFacturacaoTotal(double facturacaoTotal) {
        this.facturacaoTotal = facturacaoTotal;
    }

    public int getProdNaoComprados() {
        return this.getTotalProdNum()-this.getNumTotalProdutosComprados();
    }

    @Override
    public int getTotalClientNum() {
        return this.numClientesTotal;
    }
}
