package com.grupo19.Interfaces;

public interface IFacturacao {

    void add(ISale sale);

    //Facturacao initFacturacao(list<Sale>);
    IFacturacao clone();
    //Facturacao (Facturacao facturacao);
   // Map<int,List<Sale>> totalProdMensal();
   // Map<int,List<Sale>> totalFactMensal();
    //boolean contains(Facturacao fact,);
   // Map<int,List<Sale> > factPorMesClient(Client client);
    public double valorTotalFactMensal(int month);
    //double valorTotalProdMensal(int month);
    public int totalSalesPerProductPerMonth(int month, Product prod);
    public Map<Int,String> topProdPerClient( Client c);
}


