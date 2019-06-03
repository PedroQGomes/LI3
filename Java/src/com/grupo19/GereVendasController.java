package com.grupo19;

import com.grupo19.Interfaces.IGereVendasController;
import com.grupo19.Interfaces.IGereVendasModel;
import com.grupo19.Interfaces.IGereVendasView;
import com.grupo19.Interfaces.IProduct;

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
        List<IProduct> tmp =  model.getCatProd().listOfProductsThatStartWithLetter(l.charAt(0));
        view.addToStringBrowser(tmp.stream().map(IProduct::toString).collect(Collectors.toList()));
        view.setRow(5);
        view.setCol(5);
        view.updateMenu(Menu.STRINGBROWSER);
        view.updateView();
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
                break;
            case 5:
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
        do {
            view.updateView();
            reactToInput(view.getChoice());
        }
        while(!view.exit());
    }
}
