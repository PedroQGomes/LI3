package com.grupo19;

import com.grupo19.Interfaces.*;

import java.util.List;
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
        sb.append("Para o mês ").append(month+1).append(" :").append("\n");
        int totalSum = 0;
        for(int i = 0; i<GereVendasModel.getNumFiliais() ; i++) {
            Tuple<Integer,Integer> tmp = model.totalNumbOfSalesInMonthAndClientsBought(month,i);
            if(tmp == null) continue;
            totalSum += tmp.getFirstElem();
            sb.append("Na Filial ").append(i+1).append(" foram feitas ").append(tmp.getFirstElem()).append(" vendas por ").append(tmp.getSecondElem()).append(" clientes diferentes").append("\n");
        }
        sb.append("Dá um total de ").append(totalSum).append(" vendas neste mês\n");
        view.setTimeQueue(Crono.stop());
        view.showLine(sb.toString());


    }

    private void query5(){
        String l = GereVendasView.getUserInputString();
        if(!model.getCatClient().contains(l))return;
        Crono.start();
        List<String> tmp = model.getListOfProductsBoughtOfClient(l);
        view.setTimeQueue(Crono.stop());
        view.addToStringBrowser(tmp);
        view.setRow(5);
        view.setCol(10);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
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

    private void query4() {

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
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
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
