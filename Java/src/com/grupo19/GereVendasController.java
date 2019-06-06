package com.grupo19;

import com.grupo19.Interfaces.*;

import java.util.Collections;
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

    private void productsNoOneBought() {
        List <IProduct> tmp = model.productsNoOneBoughtModel();
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
            Tuple<Integer, Integer> tmp = model.totalNumbOfSalesInMonthAndClientsBought(month, i);
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
        List<Tuple<Integer,Integer>> tmp = model.totalPurchasesOfAClientPerYear(client);
        if(tmp == null) view.showLine("Cliente não existe");
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 12; i++) {
            Tuple<Integer,Integer> tmpTuple = null;
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


    private void query4(){
        String l = GereVendasView.getUserInputString();
        if(!model.getCatProd().contains(l))return;
        List<List<Double>> lista = model.getMumClientAndFacturacao(l);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 12 ;i++){
            sb.append("Produto ")
                    .append(l)
                    .append(" no mes")
                    .append(i).append("foi comprado ")
                    .append(lista.get(i).get(0)).append(" vez(es),por ")
                    .append(lista.get(i).get(1)).append("clientes diferentes")
                    .append(" e facturou :").append(lista.get(i).get(2))
                    .append("\n");

        }
        view.showLine(sb.toString());
    }




    private void query5(){
        String l = GereVendasView.getUserInputString();
        if(!model.getCatClient().contains(l))return;
        List<String> tmp = model.getListOfProductsBoughtOfClient(l);
        view.addToStringBrowser(tmp);
        view.setRow(5);
        view.setCol(10);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
    }


    private void query7(){
        List<List<String>> lista = model.getListOfClientsWhoMostBought();
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
        int x = GereVendasView.getUserInputInt();
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<String, Set<String>>> lista = model.getClientsHowBoughtMostOften(x);
        for(int i = 0; i < x ;i++){
            sb.append("Cliente ")
                    .append(lista.get(i).getKey())
                    .append(" comprou:")
                    .append(lista.get(i).getValue().size()).append(" vez(es)")
                    .append("\n");

        }
        view.showLine(sb.toString());
    }


    private void reactToInput(int choice) {
        switch (choice) {
            case 1:
                productsNoOneBought();
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
                break;
            case 7:
                query7();
                break;
            case 8:
                query8();
                break;
            case 9: // faturacao
                break;
            case 10:
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
