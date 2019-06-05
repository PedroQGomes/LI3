package com.grupo19;

import com.grupo19.Interfaces.*;
import com.grupo19.Models.*;

import java.util.List;

public class GereVendasModel implements IGereVendasModel {
    private static int NUM_FILIAIS = 3;
    private String fichVendas;
    private ICatProd catProd;
    private ICatClient catClient;
    private IFacturacao facturacao;
    private IFilial[] filiais;
    private IEstatisticas estat;
    private transient double timeOfLoadData;

    public GereVendasModel() {
        catProd = new CatProd();
        catClient = new CatClient();
        facturacao = new Facturacao();
        filiais = new IFilial[NUM_FILIAIS];
        fichVendas = "Vendas_1M.txt";
        for(int  i = 0 ; i<NUM_FILIAIS ; i++) filiais[i] = new Filial();
        estat = new Estatistica();
    }

    public  static IGereVendasModel loadData ( ) {
        IGereVendasModel model = new GereVendasModel();
        Crono.start();
        List<String> config = Input.lerLinhasWithBuff("config.txt");
        if(config.isEmpty()) return null;
        List<String> produtos = Input.lerLinhasWithBuff(config.get(0));
        List<String> clientes = Input.lerLinhasWithBuff(config.get(1));
        model.setFichVendas(config.get(2));
        model.getEstatatistica().setNumProdutosTotal(produtos.size());
        model.getEstatatistica().setNumClientesTotal(clientes.size());
        try {
            GereVendasModel.NUM_FILIAIS = Integer.parseInt(config.get(3));
        } catch (NumberFormatException e) {
            GereVendasModel.NUM_FILIAIS = 3;
        }
        produtos.forEach(p -> GereVendasModel.addToCatProdFromString(p,model));
        clientes.forEach(c -> GereVendasModel.addToCatClientFromString(c,model));
        loadVendas(model);
        model.setTimeOfLoadData(Crono.stop());
        return model;
    }

    public void setFichVendas(String fichVendas) {
        this.fichVendas = fichVendas;
    }


    private static void loadVendas(IGereVendasModel model) {
        int numVendasValidas = 0;
        int vendasZero = 0;
        List<String> vendas = Input.lerLinhasWithBuff(model.getFichVendas());
        model.getEstatatistica().setNumVendasTotal(vendas.size());
        for(String l : vendas) {
            ISale tmp = processSale(l,model);
            if(tmp != null) {
                model.getCatClient().updateClientBought(tmp.getClient(),tmp.getFilial());
                model.getCatProd().updateProductBought(tmp.getProduct(),tmp.getFilial(),tmp.getUnits());
                numVendasValidas++;
                model.getFiliais()[tmp.getFilial()-1].add(tmp);
                model.getFacturacao().add(tmp);
                if(tmp.getPrice() == 0.0)  vendasZero++;
            }
        }
        model.getEstatatistica().setNumVendasValidas(numVendasValidas);
        model.getEstatatistica().setFacturacaoTotal(model.getFacturacao().facturacaoTotal());
        model.getEstatatistica().setNumClientesNaoCompraram(model.getCatClient().clientsNeverBought().size());
        model.getEstatatistica().setNumTotalProdutosComprados(model.getEstatatistica().getTotalProdNum() - model.getCatProd().productsNeverBought().size());
        model.getEstatatistica().setNumTotalDeComprasValorNulo(vendasZero);
    }

    public IFilial[] getFiliais() {
        return this.filiais;
    }
    public IFacturacao getFacturacao() {
        return this.facturacao;
    }

    public  List<IClient> listOfClientsThatBoughtInAllFilials() {
        return getCatClient().listOfClientsThatBoughtInAllFilials();
    }
    public  List<IClient> listOfClientsThatDBoughtInAllFilials() {
        return getCatClient().listOfClientsThatDBoughtInAllFilials();
    }

    public ICatProd getCatProd ( ) {
        return catProd;
    }

    public ICatClient getCatClient() {
        return this.catClient;
    }

    private static ISale processSale(String l,IGereVendasModel model) {
    ISale sale = Sale.readLineToSale(l);
    if(sale == null) return null;
    if(sale.isValid(model.getCatProd(),model.getCatClient()))
        return sale;
    else return null;
    }

    public double getTimeOfLoadData() {
        return timeOfLoadData;
    }

    public void setTimeOfLoadData(double time) {
        this.timeOfLoadData = time;
    }

    private static void addToCatProdFromString(String l,IGereVendasModel model) {
        IProduct tmp = new Product(l);
        if(!tmp.isValid()) return;
        model.getCatProd().add(tmp);
    }
    private static void addToCatClientFromString(String l,IGereVendasModel model) {
        IClient tmp = new Client(l);
        if(!tmp.isValid()) return;
        model.getCatClient().add(tmp);
    }

    public List<IProduct> listOfProductsWithLetter(char letter) {
        return this.getCatProd().listOfProductsThatStartWithLetter(letter);
    }

    public List<IProduct> productsNoOneBoughtModel() {
        return this.getCatProd().productsNeverBought();
    }


    public static int getNumFiliais ( ) {
        return NUM_FILIAIS;
    }

    public String getFichVendas() {
        return fichVendas;
    }

    public IEstatisticas getEstatatistica() {
        return this.estat;
    }


}
