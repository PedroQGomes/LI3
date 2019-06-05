package com.grupo19;
import static java.lang.System.out;

import com.grupo19.Interfaces.IEstatisticas;
import com.grupo19.Interfaces.IGereVendasView;

import java.text.DecimalFormat;
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
    private double timeQueue;

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
     * Lê o input(String) do user
     * @return
     */
    public static String getUserInputString(String s) {
        out.print(s + " ");
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
     * Lê o input(int) do user
     * @return
     */
    public static int getUserInputInt(String s) {
        out.print(s + " ");
        return Input.lerInt();
    }

    public static int getMonthFromInput() {
        int month = getUserInputInt("Insira o mês:");
        while(month < 1 || month > 12) month = getUserInputInt("Insira um mês válido:");
        return month;
    }

    /**
     * Adiciona uma lista de strings ao navegador de strings
     * @param stringsList
     */
    public void addToStringBrowser (List<String> stringsList ) {
        if(stringsList.size() == 0) return;
        stringBrowser.clear();
        stringBrowser.addAll(stringsList);
        stringBrowser.sort(String::compareTo); //TODO: SE FOR UMA ARVORE NAO DEVO PRECISAR DE INVOCAR ESTE METODO PARA DAR SORT
    }

    @Override
    public void showInfoView(String fichName, IEstatisticas estatistica) {
        StringBuilder sb = new StringBuilder("Ficheiro lido: ");
        sb.append(fichName).append("\n");
        sb.append("Número total de registos de venda errados: ").append(estatistica.getNumVendasInvalidas()).append("\n");
        sb.append("Número total de produtos: ").append(estatistica.getTotalProdNum()).append("\n");
        sb.append("Número total de produtos comprados: ").append(estatistica.getNumTotalProdutosComprados()).append("\n");
        sb.append("Número total de produtos não comprados: ").append(estatistica.getProdNaoComprados()).append("\n");
        sb.append("Número total de clientes: ").append(estatistica.getTotalClientNum()).append("\n");
        sb.append("Número total de clientes que nada compraram: ").append(estatistica.getNumClientesNaoCompraram()).append("\n");
        sb.append("Número de commpras de valor total igual a 0.0: ").append(estatistica.getNumTotalDeComprasValorNulo()).append("\n");
        DecimalFormat df = new DecimalFormat("###.##");
        sb.append("Faturação total: ").append(df.format(estatistica.getFacturacaoTotal()));
        header();
        out.println(sb.toString());
        Input.lerString();

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
    private void header() {
        StringBuilder sb = new StringBuilder("Programa de Gestao de Vendas Realizado por Jose Santos, Pedro Queiros e Alexandre Costa             Tempo Decorrido:");
        DecimalFormat df = new DecimalFormat("###.##");
        sb.append(df.format(this.timeQueue)).append(" segundos\n");
        out.println(sb.toString());
    }



    public void updateMenu (Menu menu) {
        this.menu = menu;
    }

    public void updateView () {
        header();
        switch(this.menu) {
            case STRINGBROWSER:
                navigate();
                break;
            default:
                showMenuAsList(menu);
                choice = Input.lerInt();
                if(choice >= menu.getMenuOptions().length) {
                    this.menu = Menu.MAINMENU;
                }
                break;
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

    public void setTimeQueue(double timeQueue) {
        this.timeQueue = timeQueue;
    }


    public int getChoice() {return this.choice;}

    public void showLine (String line) {
        System.out.println(line);
        Input.lerString();
    }

    public void navigate ( ) {
        if(this.stringBrowser.size() == 0){
            out.println("Não existem resultados");
            return;
        }
        while(this.getCurrentMenu() == Menu.STRINGBROWSER) {
            this.printStringsBrowser();
            switch (Input.lerInt()) {
                case 1:
                    if(page>0)
                    page--;
                    break;
                case 2:
                    if(stringBrowser.size() > (cursor)+(row*col))
                    page++;
                    break;
                default:
                    this.menu = Menu.MAINMENU;
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
