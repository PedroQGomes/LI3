package com.grupo19;

import com.grupo19.Interfaces.*;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GereVendasController implements IGereVendasController {
    private IGereVendasModel model;
    private IGereVendasView view;

    public void setModel (IGereVendasModel model) {
        this.model = model;
    }


    public void setView (IGereVendasView view) {
        this.view = view;

    }

    public void productsThatStartWithLetter() {
        String l = GereVendasView.getUserInputString();
        List<IProduct> tmp = model.listOfProductsWithLetter(l.charAt(0));
        view.addToStringBrowser(tmp.stream().map(IProduct::toString).collect(Collectors.toList()));
        view.setRow(5);
        view.setCol(5);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
    }

    private void query1() {
        Crono.start();
        List <IProduct> tmp = model.productsNoOneBoughtModel();
        view.setTimeQueue(Crono.stop());
        view.addToStringBrowser(tmp.stream().map(IProduct::getCodigo).collect(Collectors.toList()));
        view.setRow(5);
        view.setCol(10);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
    }

    private void clientsThatBoughtInAllFilials() {
        List<IClient> tmp = model.listOfClientsThatBoughtInAllFilials();
        if(!tmp.isEmpty()) view.addToStringBrowser(tmp.stream().map(IClient::getCodigo).collect(Collectors.toList()));
        view.setRow(5);
        view.setCol(10);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
    }

    private void showClientsThatDBoughtNorProd() {
        int clientsDBought = model.listOfClientsThatDBoughtInAllFilials().size();
        int productsDBought = model.productsNoOneBoughtModel().size();
        StringBuilder sb = new StringBuilder("Número de clientes que não compraram: ");
        sb.append(clientsDBought).append(" Número de produtos que nunca foram comprados: ").append(productsDBought);
        view.showLine(sb.toString());
    }

    private void query2() {
        int month = GereVendasView.getMonthFromInput() -1;
        Crono.start();
        StringBuilder sb = new StringBuilder();
        sb.append("Para o mês ").append(month + 1).append(" :").append("\n");
        int totalSum = 0;
        for (int i = 0; i < GereVendasModel.getNumFiliais(); i++) {
            ITuple<Integer, Integer> tmp = model.totalNumbOfSalesInMonthAndClientsBought(month, i);
            if (tmp == null) continue;
            totalSum += tmp.getFirstElem();
            sb.append("Na Filial ").append(i + 1).append(" foram feitas ").append(tmp.getFirstElem()).append(" vendas por ").append(tmp.getSecondElem()).append(" clientes diferentes").append("\n");
        }
        sb.append("Dá um total de ").append(totalSum).append(" vendas neste mês\n");
        view.setTimeQueue(Crono.stop());
        view.showLine(sb.toString());


    }



    private void query3() { //TODO : Round GASTOU
        String client = GereVendasView.getUserInputString("Insira um código de cliente");
        Crono.start();
        List<ITuple<Integer,Integer>> tmp = model.totalPurchasesOfAClientPerYear(client);
        if(tmp == null) view.showLine("Cliente não existe");
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 12; i++) {
            ITuple<Integer,Integer> tmpTuple = null;
            if(tmp.size() > i) tmpTuple = tmp.get(i);
            if(tmpTuple == null) continue;
            sb.append("Mês ")
                    .append(i+1)
                    .append(" :")
                    .append("   Fez ").append(tmpTuple.getFirstElem())
                    .append(" compras de ")
                    .append(tmpTuple.getSecondElem())
                    .append(" produtos diferentes e gastou ")
                    .append(model.totalFaturadoPClientPMonth(client,i)).append("\n");
        }
        view.setTimeQueue(Crono.stop());
        view.showLine(sb.toString());
    }


    private void query4(){ //TODO: melhorar apresentação
        String l = GereVendasView.getUserInputString();
        Crono.start();
        List<List<Double>> lista = model.getNumClientAndFacturacao(l);
        view.setTimeQueue(Crono.stop());
        StringBuilder sb = new StringBuilder();
        if(lista.isEmpty()) {
            view.showLine("Não existe esse produto!");
            return;
        }
        for(int i = 0; i < 12 ;i++){
            if(lista.get(i).isEmpty()) {
                sb.append("O Produto ").append(l).append(" não foi comprado no mês ").append(i+1).append("\n");
                continue;
            }
            sb.append("Produto ")
                    .append(l)
                    .append(" no mes")
                    .append(i+1).append(" foi comprado ")
                    .append(Math.round(lista.get(i).get(0))).append(" vez(es),por ")
                    .append(Math.round(lista.get(i).get(1))).append(" clientes diferentes")
                    .append(" e facturou :").append(lista.get(i).get(2))
                    .append("\n");

        }

        view.showLine(sb.toString());
    }




    private void query5(){ //TODO: testar implementaçao algo confusa
        String l = GereVendasView.getUserInputString();
        Crono.start();
        List<String> tmp = model.getListOfProductsBoughtOfClient(l);
        view.setTimeQueue(Crono.stop());
        view.addToStringBrowser(tmp);
        view.setRow(5);
        view.setCol(10);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
    }

    private void query6() {
        int n = GereVendasView.getUserInputInt("Insira o  número de vezes: ");
        Crono.start();
        List<ITuple<String,Integer>> tmp = model.productsMostSellAndNumberOfClients(n);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< n ; i++) {
            ITuple<String,Integer> tuple = tmp.get(i);
            sb.append(i+1).append("º - Produto ").append(tuple.getFirstElem()).append(" foi comprado por ").append(tuple.getSecondElem()).append(" clientes\n");
        }
        view.setTimeQueue(Crono.stop());
        view.showLine(sb.toString());
    }


    private void query7(){
        Crono.start();
        List<List<String>> lista = model.getListOfClientsWhoMostBought();
        view.setTimeQueue(Crono.stop());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < GereVendasModel.getNumFiliais(); i++) {
            sb.append("Filial")
                    .append(i+1)
                    .append(" :")
                    .append(lista.get(i))
                    .append("\n");
        }
        view.showLine(sb.toString());

    }

    private void query8(){
        int x = GereVendasView.getUserInputInt("Insira o número de clientes pretendido:");
        Crono.start();
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<String, Set<String>>> lista = model.getClientsHowBoughtMostOften(x);
        view.setTimeQueue(Crono.stop());
        for(int i = 0; i < x ;i++){
            sb.append("Cliente ")
                    .append(lista.get(i).getKey())
                    .append(" comprou:")
                    .append(lista.get(i).getValue().size()).append(" vez(es)")
                    .append("\n");

        }
        view.showLine(sb.toString());
    }
    //Dado o código de um produto que deve existir, determinar o conjunto dos X clientes
    //que mais o compraram e, para cada um, qual o valor gasto
    private void query9() {
        Crono.start();
        String l = GereVendasView.getUserInputString();
        int tamanho = GereVendasView.getUserInputInt();
        List<Map.Entry<String, ITuple<Integer,Double>>> lista = model.getXClientsWhoMostBoughtProduct(l,tamanho);
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < lista.size(); i++) {
            sb.append("O clinte ").append(lista.get(i).getKey()).append(" comprou o produto ")
                    .append(lista.get(i).getValue().getFirstElem())
                    .append(" vez(es) e gastou um total de ")
                    .append(lista.get(i).getValue().getSecondElem())
                    .append(" \n");
        }
        view.showLine(sb.toString());
        view.setTimeQueue(Crono.stop());

    }

    private void query10() { //TODO: Melhorar Apresentaçao
        String prod = GereVendasView.getUserInputString("Insira o código de Produto:");
        List<String> stringList = new ArrayList<>();
        stringList.add("Filiais");
        for(int j = 1; j <= 12 ; j++) stringList.add("Mês "+j);
        Crono.start();
        List<List<Double>> tmp = model.facturacaoPerProdPerFilialPerMonth(prod);
        view.setTimeQueue(Crono.stop());
        for(int i=0; i<GereVendasModel.getNumFiliais(); i++) {
            stringList.add("Filial "+(i+1));
            for(int j = 0 ; j<12; j++) {
                List<Double> doubleList = tmp.get(j);
                if(doubleList.isEmpty()) {
                    stringList.add(((Double)0.0).toString());
                    continue;
                }
                stringList.add(doubleList.get(i).toString());
            }
        }
        view.addToStringBrowser(stringList);
        view.setCol(GereVendasModel.getNumFiliais()+1);
        view.setRow(13);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
    }



    private void reactToInput(int choice) {
        switch (choice) {
            case 1:
                query1();
                break;
            case 2:
                query2();
                break;
            case 3:
                query3();
                break;
            case 4:
                query4();
                break;
            case 5:
                query5();
                break;
            case 6: // faturacao
                query6();
                break;
            case 7:
                query7();
                break;
            case 8:
                query8();
                break;
            case 9: // faturacao
                query9();
                break;
            case 10:
                query10();
                break;
            default:
                break;

        }
    }



    public void init ( ) {
        view.setTimeQueue(model.getTimeOfLoadData());
        view.showInfoView(model.getFichVendas(),model.getEstatatistica());
        do {
            view.updateView();
            reactToInput(view.getChoice());
        }
        while(!view.exit());
    }



}
