package com.grupo19;
import static java.lang.System.out;
import com.grupo19.Interfaces.IGereVendasView;

import java.util.ArrayList;
import java.util.List;

public class GereVendasView implements IGereVendasView {
    private Menu menu;
    private int choice;
    private List<String> stringBrowser;
    private int cursor;
    private int page;
    private int row;
    private int col;

    /**
     * Define quantas linhas o navegador de strings vai ter
     * @param row
     */
    public void setRow (int row) {
        this.row = row;
    }

    /**
     * Define quantas colunas o navegador de strings vai ter
     * @param col
     */
    public void setCol (int col) {
        this.col = col;
    }

    /**
     * Lê o input(String) do user
     * @return
     */
    public static String getUserInputString() {
        return Input.lerString();
    }

    /**
     * Lê o input(int) do user
     * @return
     */
    public static int getUserInputInt() {
        return Input.lerInt();
    }

    /**
     * Adiciona uma lista de strings ao navegador de strings
     * @param stringsList
     */
    public void addToStringBrowser (List<String> stringsList ) {
        stringBrowser.clear();
        stringBrowser.addAll(stringsList);
        stringBrowser.sort(String::compareTo); //TODO: SE FOR UMA ARVORE NAO DEVO PRECISAR DE INVOCAR ESTE METODO PARA DAR SORT
    }

    /**
     * Construtor da class GereVendasView
     */
    public GereVendasView() {
        updateMenu(Menu.MAINMENU);
        this.cursor = 0;
        this.page = 0;
        this.col = 10;
        this.row = 5;
        stringBrowser = new ArrayList<>();
    }

    /**
     * Imprime sempre a informação que quisermos antes do menu
     */
    private void header() { //TODO: TEMPO DE CARREGAMENTO
        System.out.println();
    }



    public void updateMenu (Menu menu) {
        this.menu = menu;
    }

    public void updateView () {
        if(this.menu == Menu.STRINGBROWSER) navigate();
        showMenuAsList(menu);
        choice = Input.lerInt();
        if(choice >= menu.getMenuOptions().length) {
        this.menu = Menu.MAINMENU;
        }
    }

    private void showMenu(String[] menuOptions) {
        for(String s:menuOptions) {
            out.println(s);
        }
        Input.lerString();
    }

    private void showMenuAsList(Menu menu) {
        String[] menuOptions = menu.getMenuOptions();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(String s:menuOptions) {
            i++;
            sb.append(i).append(" - ").append(s).append("\n");
        }
        out.print(sb.toString());
    }

    public int getChoice() {return this.choice;}

    public void navigate ( ) {
        while(this.getCurrentMenu() == Menu.STRINGBROWSER) {
            this.printStringsBrowser();
            switch (Input.lerInt()) {
                case 0:
                    this.menu = Menu.MAINMENU;
                    break;
                case 1:
                    if(page>0)
                    page--;
                    break;
                case 2:
                    if(stringBrowser.size() > (cursor)+(row*col))
                    page++;
                    break;
                default:
                    break;
            }
        }

    }

    private void printStringsBrowser() {
        cursor = page*(row*col);

        for(int i = 0; i < row ;i++){
            for(int j = 0; j < col;j++) {
                int index = ((j*row)+i+cursor);
                if(stringBrowser.size() <= index ) break;
                String tmp = stringBrowser.get(index);
                out.print(tmp);
                out.print("       ");
            }
            out.print("\n");
        }
        out.println("Total de resultados da query: " + stringBrowser.size());
        out.println("\nPrima 0 para sair");
        out.println("Prima 1 para a página anterior");
        out.println("Prima 2 para a próxima página");
    }


    public boolean choiceIsOutOfRange() {
        return (choice > this.menu.getMenuOptions().length);
    }
    public Menu getCurrentMenu() { return this.menu;}


    public boolean exit() {
        if(menu != Menu.MAINMENU) return false;
        return choice == 0 || choice > menu.getMenuOptions().length;
    }
}
