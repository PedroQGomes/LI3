package com.grupo19.Interfaces;

public interface IFacturacao {

    void add(ISale sale);

    IFacturacao initFacturacao(list<ISale>);
    IFacturacao clone();
    IFacturacao I_Facturacao();
    IFacturacao (IFacturacao facturacao);
    Map<int,collection<ISale>> totalProdMensal();
    Map<int,collection<ISale>> totalFactMensal();
    boolean contains(IFacturacao fact,);
    Map<int,collection<ISale> > factPorMesClient(IClient client);
    Double valorTotalFactMensal();
    Double valorTotalProdMensal();

}

