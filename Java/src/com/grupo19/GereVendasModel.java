package com.grupo19;

import com.grupo19.Interfaces.*;
import com.grupo19.Models.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.compare;

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



    /**
     * Metodo para dar resposta a query 2
     * @param x
     * @return
     */
    @Override
    public Tuple<Integer, Integer> totalNumbOfSalesInMonthAndClientsBought(int x, int filial) {
        return this.getFiliais()[filial].totalNumbOfSalesInMonthAndClientsBought(x);
    }

    /**
     * Query 3
     * @param client
     * @return
     */
    public List<Tuple<Integer,Integer>> totalPurchasesOfAClientPerYear(String client) {
        if(!this.getCatClient().contains(client)) return null;
        List<Tuple<Integer,Integer>> tmp = new ArrayList<>(12);
        for(int month = 0 ; month < 12; month++) {
            int total = 0;
            Set<String> stringSet = new HashSet<>();
            for(int i = 0; i<GereVendasModel.getNumFiliais(); i++) {
            Tuple<Integer, Set<String>> setTuple = this.getFiliais()[i].numOfDifferentProductsOfClientAndNumOfSales(client,month);
            stringSet.addAll(setTuple.getSecondElem());
            total += setTuple.getFirstElem();
            }
            tmp.add(new Tuple<>(total,stringSet.size()));
        }
        return tmp;
    }

    public double totalFaturadoPClientPMonth(String client,int month) {
        if(!this.getCatClient().contains(client)) return 0.0;
        double tmp = 0.0;
        for(int i = 0; i < GereVendasModel.getNumFiliais(); i++) {
            tmp += this.getFiliais()[i].totalFaturadoPerClientPerMonth(client, month);
        }
        return tmp;
    }
    public List<String> getListOfProductsBoughtOfClient(String a){
        List<Map<String,Integer>> res = new ArrayList<>();
        for(int i = 0; i< NUM_FILIAIS; i++) {
            res.add(i,filiais[i].getListOfProductsBoughtOfClient(a));
        }
        return sortIntoLista(res);

    }

    private List<String> sortIntoLista(List<Map<String,Integer>> mapa){
        Map<String,Integer> mapalist = new HashMap<>();
        List<String> lista;
        for(int i = 0; i < NUM_FILIAIS; i++){
            for(Map.Entry<String,Integer> fil : mapa.get(i).entrySet()){ // map com aritgo e numero de vezes que foi comprado
                if(mapalist.containsKey(fil.getKey())){
                    int tmp = mapalist.get(fil.getKey());
                    tmp += fil.getValue();
                    mapalist.put(fil.getKey(),tmp);
                }else{
                    mapalist.put(fil.getKey(),fil.getValue());
                }
            }
        }
        lista = mapalist.entrySet().stream().sorted((o1,o2)-> comparaEntrySets(o1,o2)).map(l-> l.getKey()).collect(Collectors.toList());
        Collections.reverse(lista);
        return lista;

    }


    private int comparaEntrySets(Map.Entry<String,Integer> fst,Map.Entry<String,Integer> snd){
        if(fst.getValue().equals(snd.getValue())){
            return fst.getKey().compareTo(snd.getKey());
        }
        if(fst.getValue() > snd.getValue()) return 1;
        return -1;
    }

    public List<List<String>> getListOfClientsWhoMostBought(){
        List<List<String>> res = new ArrayList<>();
        for(int i = 0; i<NUM_FILIAIS; i++){
            res.add(filiais[i].getListOfClientsWhoMostBought());
        }
        return res;
    }


    // queiry 8 interativa
    public List<Map.Entry<String,Set<String>>> getClientsHowBoughtMostOften(int x){
        List<Map<String,Set<String>>> lista = new ArrayList<>();
        for(int i = 0; i < NUM_FILIAIS; i++){
            lista.add(filiais[i].getClientsHowBoughtMostOften());
        }
        return getWhoMostBought(lista,x);
    }

    // metodo aussiliar da queiry 8
    private List<Map.Entry<String,Set<String>>> getWhoMostBought(List<Map<String,Set<String>>> lista ,int x){
        Map<String,Set<String>> mapa = new HashMap<>();
        List<Map.Entry<String,Set<String>>> res;
        for(int i = 0 ; i<NUM_FILIAIS;i++){
            for(Map.Entry<String,Set<String>> entry : lista.get(i).entrySet()){
                if(mapa.containsKey(entry.getKey())){
                    addLista(entry,mapa);
                }else{
                    mapa.put(entry.getKey(),entry.getValue());
                }
            }
        }
        res = mapa.entrySet().stream().sorted((o1, o2) -> compare(o1.getValue().size(),o2.getValue().size())).collect(Collectors.toList());
        Collections.reverse(res);
        return res.stream().limit(x).collect(Collectors.toList());

    }

    // metodo aussiliar da queiry 8
    private void addLista(Map.Entry<String,Set<String>> entry,Map<String,Set<String>> mapa){
        for(String a: entry.getValue()){
            mapa.get(entry.getKey()).add(a);
        }
    }

















}
