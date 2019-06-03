package com.grupo19;

import com.grupo19.Interfaces.IGereVendasView;

import java.util.ArrayList;
import java.util.List;

public class testeView {

    public static void main (String[] args) {
        IGereVendasView view = new GereVendasView();
        List<String> stringList = new ArrayList<>();
        stringList.add("Teste");
        stringList.add("Teste");
        stringList.add("Teste");
        stringList.add("Teste");
        stringList.add("Teste");
        view.setCol(5);
        view.setRow(5);
        view.addToStringBrowser(stringList);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
    }
}
