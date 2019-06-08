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

    /**
     * construtor vazio do GereVendasModel
     */
    public GereVendasModel() {
        catProd = new CatProd();
        catClient = new CatClient();
        facturacao = new Facturacao();
        filiais = new IFilial[NUM_FILIAIS];
        fichVendas = "Vendas_1M.txt";
        for (int i = 0; i < NUM_FILIAIS; i++) filiais[i] = new Filial();
        estat = new Estatistica();
    }


    /**
     * metodo que carrega os dados dos ficheiros txt
     * @return model
     */
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
        //model.saveState("data.tmp"); //TODO ATIVAR ISTO
        model.setTimeOfLoadData(Crono.stop());
        return model;
    }

    /**
     * define o nome do ficheiro de vendas
     * @param fichVendas
     */
    public void setFichVendas(String fichVendas) {
        this.fichVendas = fichVendas;
    }


    /**
     * atualiza a info estatica
     */
    public void updateStaticInfo() {
        this.estat.setFacturacaoTotal(this.facturacao.facturacaoTotal());
        this.estat.setNumClientesNaoCompraram(this.catClient.clientsNeverBought().size());
        this.estat.setNumTotalProdutosComprados(estat.getTotalProdNum() - this.catProd.productsNeverBought().size());
    }


    /**
     * carrega as vendas
     * @param model
     * @param estat
     */
    private static void loadVendas(IGereVendasModel model, IEstatisticas estat) {
        int numVendasValidas = 0;
        int vendasZero = 0;
        List<String> vendas = Input.lerLinhasWithBuff(model.getFichVendas());
        estat.setNumVendasTotal(vendas.size());
        for (String l : vendas) {
            ISale tmp = processSale(l, model);
            if (tmp != null) {
                model.getCatClient().updateClientBought(tmp.getClient(), tmp.getFilial(),tmp.getProduct());
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

    /**
     * dá as filias
     * @return filiais
     */
    public IFilial[] getFiliais() {
        return this.filiais;
    }


    /**
     * dá a facturacao
     * @return facturacao
     */
    public IFacturacao getFacturacao() {
        return this.facturacao;
    }

    /**
     * lista de clientes que compraram em todas as filiais
     * @return lista
     */
    public List<IClient> listOfClientsThatBoughtInAllFilials() {
        return getCatClient().listOfClientsThatBoughtInAllFilials();
    }

    /**
     * lista de clientes que nao compraram em todas as filiais
     * @return lista
     */
    public List<IClient> listOfClientsThatDBoughtInAllFilials() {
        return getCatClient().listOfClientsThatDBoughtInAllFilials();
    }

    /**
     * dá o catalogo de produtos
     * @return IcatProd
     */
    public ICatProd getCatProd() {
        return catProd;
    }

    /**
     * dá o catalogo de clientes
     * @return ICatClient
     */
    public ICatClient getCatClient() {
        return this.catClient;
    }


    /**
     * metodo que processa a sale
     * @param l
     * @param model
     * @return Isale
     */
    private static ISale processSale(String l, IGereVendasModel model) {
        ISale sale = Sale.readLineToSale(l);
        if (sale == null) return null;
        if (sale.isValid(model.getCatProd(), model.getCatClient()))
            return sale;
        else return null;
    }

    /**
     * dá o tempo que demorou a carregar os dados
     * @return double
     */
    public double getTimeOfLoadData() {
        return timeOfLoadData;
    }

    /**
     * define o tempo que demorou a carregar os dados
     * @param time
     */
    public void setTimeOfLoadData(double time) {
        this.timeOfLoadData = time;
    }


    /**
     * adiciona ao catalogo de produtos um produto pela string
     * @param l
     * @param model
     */
    private static void addToCatProdFromString(String l, IGereVendasModel model) {
        IProduct tmp = new Product(l);
        if (!tmp.isValid()) return;
        model.getCatProd().add(tmp);
    }

    /**
     * adiciona ao catalogo de clientes um cliente pela string
     * @param l
     * @param model
     */
    private static void addToCatClientFromString(String l, IGereVendasModel model) {
        IClient tmp = new Client(l);
        if (!tmp.isValid()) return;
        model.getCatClient().add(tmp);
    }


    /**
     * lista de produtos que ninguem comprou
     * @return lista
     */
    public List<IProduct> productsNoOneBoughtModel() {
        return this.getCatProd().productsNeverBought();
    }


    /**
     * retorna o numero de filiais
     * @return int
     */
    public static int getNumFiliais() {
        return NUM_FILIAIS;
    }

    /**
     * dá o nome do ficheiro de vendas
     * @return string
     */
    public String getFichVendas() {
        return fichVendas;
    }

    /**
     * dá a informacao estatica
     * @return estatisticas
     */
    public IEstatisticas getEstatatistica() {
        return this.estat;
    }


    /**
     * Metodo para dar resposta a query 2
     *
     * @param x
     * @return tuple de inteiro inteiro
     */
    @Override
    public ITuple<Integer, Integer> totalNumbOfSalesInMonthAndClientsBought(int x, int filial) {
        return this.getFiliais()[filial].totalNumbOfSalesInMonthAndClientsBought(x);
    }

    /**
     * Query 3
     *
     * @param client
     * @return lista
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

    /**
     * totol faturado por um cliente num dado month
     * @param client
     * @param month
     * @return double
     */
    public double totalFaturadoPClientPMonth(String client, int month) {
        if (!this.getCatClient().contains(client)) return 0.0;
        double tmp = 0.0;
        for (int i = 0; i < GereVendasModel.getNumFiliais(); i++) {
            tmp += this.getFiliais()[i].totalFaturadoPerClientPerMonth(client, month);
        }
        return tmp;
    }


    /**
     * dá a lista de produtos comprados por um cliente
     * @param a
     * @return lista
     */
    public List<ITuple<String,Integer>> getListOfProductsBoughtOfClient(String a) {
        if (!this.getCatClient().contains(a)) return new ArrayList<>();
        List<Map<String, Integer>> res = new ArrayList<>();
        for (int i = 0; i < NUM_FILIAIS; i++) {
            res.add(i, filiais[i].getListOfProductsBoughtOfClient(a));
        }
        return sortIntoLista(res);

    }


    /**
     * ordena a lista
     * @param mapa
     * @return lista
     */
    private List<ITuple<String,Integer>> sortIntoLista(List<Map<String, Integer>> mapa) {
        Map<String, Integer> mapalist = new HashMap<>();
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
        return mapalist.entrySet().stream().sorted(this::comparaEntrySets).map(this::function).collect(Collectors.toList());

    }


    /**
     * metodo que torna um mapa num tuple
     * @param mapa
     * @return tuple
     */
    private ITuple<String, Integer> function (Map.Entry<String,Integer> mapa) {
        return new Tuple<>(mapa.getKey(),mapa.getValue());
    }


    /**
     * metodo que compara entrysets
     * @param fst
     * @param snd
     * @return int
     */
    private int comparaEntrySets(Map.Entry<String, Integer> fst, Map.Entry<String, Integer> snd) {
        if (fst.getValue().equals(snd.getValue())) {
            return fst.getKey().compareTo(snd.getKey());
        }
        if (snd.getValue() > fst.getValue()) return 1;
        return -1;
    }


    /**
     * dá a lista de cliente que mais compraram
     * @return lista
     */
    public List<List<String>> getListOfClientsWhoMostBought() {
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < NUM_FILIAIS; i++) {
            res.add(filiais[i].getListOfClientsWhoMostBought());
        }
        return res;
    }


    /**
     * dá os clientes que compraram mais
     * @param x
     * @return lista
     */
    public List<ITuple<String,Integer>> getClientsWhoBoughtMostOften(int x) {
        return this.catClient.listOfClientsWhoBoughtMost(x);
    }



    /**
     * dá o numero de clientes e a facturaçao
     * @param product
     * @return lista
     */
    public List<List<Double>> getNumClientAndFacturacao(String product) {
        if (!this.getCatProd().contains(product)) return new ArrayList<>();
        return this.facturacao.getNumClientAndFacturacao(product);
    }


    /**
     * produtos que mais venderam e o numero dos clientes distintos que o compraram
     * @param n
     * @return lista
     */
    public List<ITuple<String, Integer>> productsMostSellAndNumberOfClients(int n) {
        List<ITuple<String, Integer>> res = new ArrayList<>(n);
        List<String> productsMostSellStrings = this.getCatProd().productsMostSell(n);
        for (String l : productsMostSellStrings) {
            res.add(new Tuple<>(l, this.getFacturacao().numberOfClientsWhoBought(l)));
        }
        return res;
    }


    /**
     * facturaçao por produto por filial e por mes
     * @param prod
     * @return lista
     */
    public List<List<Double>> facturacaoPerProdPerFilialPerMonth(String prod) {
        return this.getFacturacao().facturacaoPerProdPerFilialPerMonth(prod);
    }

    /**
     * recupera o estado da app
     * @param fichObject
     * @return model
     */
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

    /**
     * salva o estado do programa na app
     * @param fichObject
     */
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


    /**
     * da os X clientes que mais compraram um dado produto
     * @param produto
     * @param tamanho
     * @return lista
     */
    public List<Map.Entry<String, ITuple<Integer,Double>>> getXClientsWhoMostBoughtProduct(String produto, int tamanho){
        if(!this.catProd.contains(produto)) return new ArrayList<>();
        return this.facturacao.getXClientsWhoMostBoughtProduct(produto,tamanho);
    }
}















