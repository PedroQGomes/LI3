package com.grupo19;

import com.grupo19.Interfaces.IGereVendasController;
import com.grupo19.Interfaces.IGereVendasModel;
import com.grupo19.Interfaces.IGereVendasView;

public class GereVendasAppMVC {

    public static void main(String[] args) {
    IGereVendasModel model = new GereVendasModel();
    model.loadData();
    IGereVendasController controller = new GereVendasController();
    IGereVendasView view = new GereVendasView();
    controller.setModel(model);
    controller.setView(view);
    controller.init();
    System.exit(0);
    }
}
