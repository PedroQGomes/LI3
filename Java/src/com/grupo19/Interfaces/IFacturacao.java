package com.grupo19.Interfaces;

public interface IFacturacao {

    void add(ISale sale);

    Facturacao initFacturacao(list<Sale>);
    Facturacao clone();
    Facturacao (Facturacao facturacao);
    Map<int,List<Sale>> totalProdMensal();
    Map<int,List<Sale>> totalFactMensal();
    boolean contains(Facturacao fact,);
    Map<int,List<Sale> > factPorMesClient(Client client);
    Double valorTotalFactMensal();
    Double valorTotalProdMensal();

}


