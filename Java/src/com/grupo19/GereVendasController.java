package com.grupo19;

import com.grupo19.Interfaces.*;

import java.util.ArrayList;
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
        if(tmp.size() != 0)
        view.addToStringBrowser(tmp.stream().map(IClient::getCodigo).collect(Collectors.toList()));
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


    private void querie5(){
        String l = GereVendasView.getUserInputString();
        List<String> lista = new ArrayList<>();
        for(int i = 0; i < GereVendasModel.getNumFiliais();i++){

        }



    }

    private void reactToInput(int choice) {
        switch (choice) {
            case 1:
                productsThatStartWithLetter();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                clientsThatBoughtInAllFilials();
                break;
            case 5:
                querie5();
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
            case 11:
                break;
            default:
                break;

        }
    }

    public void init ( ) {
        //String tmp = constructStringToInit();
        view.setTimeQueue(model.getTimeOfLoadData());
        view.showInfoView(model.getFichVendas(),model.getEstatatistica());
        do {
            view.updateView();
            reactToInput(view.getChoice());
        }
        while(!view.exit());
    }

   /* private String constructStringToInit() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ficheiro: ").append(model.getFichVendas()).append("\n");
        sb.append("Número total de registos de venda errados: ").append(model.getNumVendasInvalidas()).append("\n");
        sb.append("Número total de produtos: ").append().append("\n");
        sb.append("N")
    } */
}
