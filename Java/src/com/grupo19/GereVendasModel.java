package com.grupo19;

import com.grupo19.Interfaces.*;
import com.grupo19.Models.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.compare;

public class GereVendasModel implements IGereVendasModel,Serializable {
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
        for (int i = 0; i < NUM_FILIAIS; i++) filiais[i] = new Filial();
        estat = new Estatistica();
    }

    public static IGereVendasModel loadData() {
        IGereVendasModel model = new GereVendasModel();
        IEstatisticas estat = model.getEstatatistica();
        List<String> config = Input.lerLinhasWithBuff("config.txt");
        if (config.isEmpty()) return null;
        Crono.start();
        List<String> produtos = Input.lerLinhasWithBuff(config.get(0));
        List<String> clientes = Input.lerLinhasWithBuff(config.get(1));
        model.setFichVendas(config.get(2));
        estat.setNumProdutosTotal(produtos.size());
        estat.setNumClientesTotal(clientes.size());
        try {
            GereVendasModel.NUM_FILIAIS = Integer.parseInt(config.get(3));
        } catch (NumberFormatException e) {
            GereVendasModel.NUM_FILIAIS = 3;
        }
        produtos.forEach(p -> GereVendasModel.addToCatProdFromString(p, model));
        clientes.forEach(c -> GereVendasModel.addToCatClientFromString(c, model));
        loadVendas(model,estat);
        model.saveState("data.tmp");
        model.setTimeOfLoadData(Crono.stop());
        return model;
    }

    public void setFichVendas(String fichVendas) {
        this.fichVendas = fichVendas;
    }


    public void updateStaticInfo() {
        this.estat.setFacturacaoTotal(this.facturacao.facturacaoTotal());
        this.estat.setNumClientesNaoCompraram(this.catClient.clientsNeverBought().size());
        this.estat.setNumTotalProdutosComprados(estat.getTotalProdNum() - this.catProd.productsNeverBought().size());
    }

    private static void loadVendas(IGereVendasModel model, IEstatisticas estat) {
        int numVendasValidas = 0;
        int vendasZero = 0;
        List<String> vendas = Input.lerLinhasWithBuff(model.getFichVendas());
        estat.setNumVendasTotal(vendas.size());
        for (String l : vendas) {
            ISale tmp = processSale(l, model);
            if (tmp != null) {
                model.getCatClient().updateClientBought(tmp.getClient(), tmp.getFilial());
                model.getCatProd().updateProductBought(tmp.getProduct(), tmp.getFilial(), tmp.getUnits());
                numVendasValidas++;
                model.getFiliais()[tmp.getFilial() - 1].add(tmp);
                model.getFacturacao().add(tmp);
                if (tmp.getPrice() == 0.0) vendasZero++;
            }
        }
        estat.setNumVendasValidas(numVendasValidas);
        estat.setNumTotalDeComprasValorNulo(vendasZero);
    }

    public IFilial[] getFiliais() {
        return this.filiais;
    }

    public IFacturacao getFacturacao() {
        return this.facturacao;
    }

    public List<IClient> listOfClientsThatBoughtInAllFilials() {
        return getCatClient().listOfClientsThatBoughtInAllFilials();
    }

    public List<IClient> listOfClientsThatDBoughtInAllFilials() {
        return getCatClient().listOfClientsThatDBoughtInAllFilials();
    }

    public ICatProd getCatProd() {
        return catProd;
    }

    public ICatClient getCatClient() {
        return this.catClient;
    }

    private static ISale processSale(String l, IGereVendasModel model) {
        ISale sale = Sale.readLineToSale(l);
        if (sale == null) return null;
        if (sale.isValid(model.getCatProd(), model.getCatClient()))
            return sale;
        else return null;
    }

    public double getTimeOfLoadData() {
        return timeOfLoadData;
    }

    public void setTimeOfLoadData(double time) {
        this.timeOfLoadData = time;
    }

    private static void addToCatProdFromString(String l, IGereVendasModel model) {
        IProduct tmp = new Product(l);
        if (!tmp.isValid()) return;
        model.getCatProd().add(tmp);
    }

    private static void addToCatClientFromString(String l, IGereVendasModel model) {
        IClient tmp = new Client(l);
        if (!tmp.isValid()) return;
        model.getCatClient().add(tmp);
    }



    public List<IProduct> productsNoOneBoughtModel() {
        return this.getCatProd().productsNeverBought();
    }


    public static int getNumFiliais() {
        return NUM_FILIAIS;
    }

    public String getFichVendas() {
        return fichVendas;
    }

    public IEstatisticas getEstatatistica() {
        return this.estat;
    }


    /**
     * Metodo para dar resposta a query 2
     *
     * @param x
     * @return
     */
    @Override
    public ITuple<Integer, Integer> totalNumbOfSalesInMonthAndClientsBought(int x, int filial) {
        return this.getFiliais()[filial].totalNumbOfSalesInMonthAndClientsBought(x);
    }

    /**
     * Query 3
     *
     * @param client
     * @return
     */
    public List<ITuple<Integer, Integer>> totalPurchasesOfAClientPerYear(String client) {
        if (!this.getCatClient().contains(client)) return null;
        List<ITuple<Integer, Integer>> tmp = new ArrayList<>(12);
        for (int month = 0; month < 12; month++) {
            int total = 0;
            Set<String> stringSet = new HashSet<>();
            for (int i = 0; i < GereVendasModel.getNumFiliais(); i++) {
                ITuple<Integer, Set<String>> setTuple = this.getFiliais()[i].numOfDifferentProductsOfClientAndNumOfSales(client, month);
                stringSet.addAll(setTuple.getSecondElem());
                total += setTuple.getFirstElem();
            }
            tmp.add(new Tuple<>(total, stringSet.size()));
        }
        return tmp;
    }

    public double totalFaturadoPClientPMonth(String client, int month) {
        if (!this.getCatClient().contains(client)) return 0.0;
        double tmp = 0.0;
        for (int i = 0; i < GereVendasModel.getNumFiliais(); i++) {
            tmp += this.getFiliais()[i].totalFaturadoPerClientPerMonth(client, month);
        }
        return tmp;
    }

    public List<String> getListOfProductsBoughtOfClient(String a) {
        if (!this.getCatClient().contains(a)) return new ArrayList<>();
        List<Map<String, Integer>> res = new ArrayList<>();
        for (int i = 0; i < NUM_FILIAIS; i++) {
            res.add(i, filiais[i].getListOfProductsBoughtOfClient(a));
        }
        return sortIntoLista(res);

    }

    private List<String> sortIntoLista(List<Map<String, Integer>> mapa) {
        Map<String, Integer> mapalist = new HashMap<>();
        List<String> lista;
        for (int i = 0; i < NUM_FILIAIS; i++) {
            for (Map.Entry<String, Integer> fil : mapa.get(i).entrySet()) { // map com aritgo e numero de vezes que foi comprado
                if (mapalist.containsKey(fil.getKey())) {
                    int tmp = mapalist.get(fil.getKey());
                    tmp += fil.getValue();
                    mapalist.put(fil.getKey(), tmp);
                } else {
                    mapalist.put(fil.getKey(), fil.getValue());
                }
            }
        }
        lista = mapalist.entrySet().stream().sorted(this::comparaEntrySets).map(Map.Entry::getKey).collect(Collectors.toList());
        return lista;

    }


    private int comparaEntrySets(Map.Entry<String, Integer> fst, Map.Entry<String, Integer> snd) {
        if (fst.getValue().equals(snd.getValue())) {
            return fst.getKey().compareTo(snd.getKey());
        }
        if (snd.getValue() > fst.getValue()) return 1;
        return -1;
    }

    public List<List<String>> getListOfClientsWhoMostBought() {
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < NUM_FILIAIS; i++) {
            res.add(filiais[i].getListOfClientsWhoMostBought());
        }
        return res;
    }


    // queiry 8 interativa
    public List<Map.Entry<String, Set<String>>> getClientsWhoBoughtMostOften(int x) {
        List<Map<String, Set<String>>> lista = new ArrayList<>();
        for (int i = 0; i < NUM_FILIAIS; i++) {
            lista.add(filiais[i].getClientsWhoBoughtMostOften());
        }
        return getWhoMostBought(lista, x);
    }

    // metodo auxiliar da queiry 8
    private List<Map.Entry<String, Set<String>>> getWhoMostBought(List<Map<String, Set<String>>> lista, int x) {
        Map<String, Set<String>> mapa = new HashMap<>();
        List<Map.Entry<String, Set<String>>> res;
        for (int i = 0; i < NUM_FILIAIS; i++) {
            for (Map.Entry<String, Set<String>> entry : lista.get(i).entrySet()) {
                if (mapa.containsKey(entry.getKey())) {
                    addLista(entry, mapa);
                } else {
                    mapa.put(entry.getKey(), entry.getValue());
                }
            }
        }
        res = mapa.entrySet().stream().sorted((o1, o2) -> {
            int size1 = o1.getValue().size();
            int size2 = o2.getValue().size();
            if(size1 == size2 ) return o1.getKey().compareTo(o2.getKey());
            return compare(size2,size1);
        }).limit(x).collect(Collectors.toList());
        return res;

    }

    // metodo aussiliar da queiry 8
    private void addLista(Map.Entry<String, Set<String>> entry, Map<String, Set<String>> mapa) {
        for (String a : entry.getValue()) {
            mapa.get(entry.getKey()).add(a);
        }
    }


    public List<List<Double>> getNumClientAndFacturacao(String product) {
        if (!this.getCatProd().contains(product)) return new ArrayList<>();
        return this.facturacao.getNumClientAndFacturacao(product);
    }

    public List<ITuple<String, Integer>> productsMostSellAndNumberOfClients(int n) {
        List<ITuple<String, Integer>> res = new ArrayList<>(n);
        List<String> productsMostSellStrings = this.getCatProd().productsMostSell(n);
        for (String l : productsMostSellStrings) {
            res.add(new Tuple<>(l, this.getFacturacao().numberOfClientsWhoBought(l)));
        }
        return res;
    }

    public List<List<Double>> facturacaoPerProdPerFilialPerMonth(String prod) {
        return this.getFacturacao().facturacaoPerProdPerFilialPerMonth(prod);
    }

    public static IGereVendasModel recoverState(String fichObject) {
        IGereVendasModel model = null;
        try {
            Crono.start();
            try(FileInputStream fis = new FileInputStream(fichObject)) {
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
                model = (GereVendasModel) ois.readObject();
                model.setTimeOfLoadData(Crono.stop());
                System.out.println("Dados Lidos");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }


    public void saveState(String fichObject) {
        try {
            try(FileOutputStream fos = new FileOutputStream(fichObject)){
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(this);
                oos.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Map.Entry<String, ITuple<Integer,Double>>> getXClientsWhoMostBoughtProduct(String produto, int tamanho){
        if(!this.catProd.contains(produto)) return new ArrayList<>();
        return this.facturacao.getXClientsWhoMostBoughtProduct(produto,tamanho);
    }
}















