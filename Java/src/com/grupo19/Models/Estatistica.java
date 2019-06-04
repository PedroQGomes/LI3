package com.grupo19.Models;

import com.grupo19.Interfaces.IEstatisticas;

public class Estatistica implements IEstatisticas {

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


    public int getNumClientesTotal() {
        return numClientesTotal;
    }

    public void setNumClientesTotal(int numClientesTotal) {
        this.numClientesTotal = numClientesTotal;
    }

    @Override
    public void setNumVendasTotal(int numVendasTotal) {
        this.numVendasTotal = numVendasTotal;
    }

    public void setNumVendasValidas(int numVendasValidas) {
        this.numVendasValidas = numVendasValidas;
    }

    @Override
    public int getNumVendasValidas() {
        return 0;
    }

    @Override
    public int getNumVendasInvalidas() {
        return 0;
    }

    @Override
    public int getTotalProdNum() {
        return 0;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
        return super.toString();
    }
}
