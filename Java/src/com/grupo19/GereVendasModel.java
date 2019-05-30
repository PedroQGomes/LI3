package com.grupo19;

import com.grupo19.Interfaces.*;
import com.grupo19.Models.*;

import java.util.List;

public class GereVendasModel implements IGereVendasModel {
    private static final int NUM_FILIAIS = 3;
    private ICatProd catProd;
    private ICatClient catClient;
    private IFacturacao facturacao;
    private IFilial[] filiais;

    public GereVendasModel() {
        catProd = new CatProd();
        catClient = new CatClient();
        facturacao = new Facturacao();
        filiais = new Filial[NUM_FILIAIS];
    }

    public void loadData ( ) { //TODO : LER DO FICHEIRO CONFIG
        List<String> produtos = Input.lerLinhasWithBuff("Produtos.txt");
        List<String> clientes = Input.lerLinhasWithBuff("Clientes.txt");
        List<String> Vendas1M = Input.lerLinhasWithBuff("Vendas_1M.txt");
        produtos.forEach(this::addToCatProdFromString);
        clientes.forEach(this::addToCatClientFromString);
    }

    private void addToCatProdFromString(String l) {
        IProduct tmp = new Product(l);
        if(!tmp.isValid()) return;
        this.catProd.add(tmp);
    }
    private void addToCatClientFromString(String l) {
        IClient tmp = new Client(l);
        if(!tmp.isValid()) return;
        this.catClient.add(tmp);
    }


    public static int getNumFiliais ( ) {
        return NUM_FILIAIS;
    }
}
