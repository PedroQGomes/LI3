package com.grupo19.Interfaces;

import java.util.List;
import java.util.Map;

public interface IGereVendasModel {
    ICatProd getCatProd();
    IFilial[] getFiliais();
    IFacturacao getFacturacao();
    void setFichVendas(String fichVendas);
    void setTimeOfLoadData(double time);
    String getFichVendas();
    IEstatisticas getEstatatistica();
    double getTimeOfLoadData();
    ICatClient getCatClient();
    List<IProduct> listOfProductsWithLetter(char letter);
    List<IProduct> productsNoOneBoughtModel();
    List<IClient> listOfClientsThatBoughtInAllFilials();
    List<IClient> listOfClientsThatDBoughtInAllFilials();
    List<String> getListOfProductsBoughtOfClient(String a);

}
