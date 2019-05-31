package com.grupo19;

public enum Menu {
    MAINMENU(new String[] {"OLA"});
    private String[] menuOptions;
    private Menu(String[] menuOptions) {
       this.menuOptions = menuOptions;
    }

    public String[] getMenuOptions ( ) {
        return menuOptions;
    }
}
