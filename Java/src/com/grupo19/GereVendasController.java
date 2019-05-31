package com.grupo19;

import com.grupo19.Interfaces.IGereVendasController;
import com.grupo19.Interfaces.IGereVendasModel;
import com.grupo19.Interfaces.IGereVendasView;

public class GereVendasController implements IGereVendasController {
    private IGereVendasModel model;
    private IGereVendasView view;

    public void setModel (IGereVendasModel model) {
        this.model = model;
    }


    public void setView (IGereVendasView view) {
        this.view = view;

    }


    public void init ( ) {
        //System.out.println(model.getCatProd());
       /* while(true) {
            view.show();
        }  */
    }
}
