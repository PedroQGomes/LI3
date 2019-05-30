package com.grupo19;
import static java.lang.System.out;
import com.grupo19.Interfaces.IGereVendasView;

public class GereVendasView implements IGereVendasView {


    public void show ( ) {
       showMenu(Menu.MAINMENU.getMenuOptions());
    }

    public void showMenu(String[] menuOptions) {
        for(String s:menuOptions) {
            out.println(s);
        }
        Input.lerString();
    }


    public void navigate ( ) {

    }
}
