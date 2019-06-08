package com.grupo19.Interfaces;

import com.grupo19.Menu;

import java.util.List;

public interface IGereVendasView {

    /**
     * permite percorrer os dados consultados
     */
    void navigate();

    /**
     * altera o modo de visualizacao do menu
     */
    void updateView ();

    /**
     * altera o menu
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * Define quantas linhas o navegador de strings vai ter
     * @param row
     */
    void setRow(int row);

    /**
     * Define quantas colunas o navegador de strings vai ter
     * @param col
     */
    void setCol(int col);

    /**
     * Adiciona uma lista de strings ao navegador de strings
     * @param stringsList
     */
    void addToStringBrowser (List<String> stringList );

    /**
     * getter da opcao do menu
     * @return
     */
    int getChoice();

    /**
     * método para fechar o menu
     * @return
     */
    boolean exit();

    /**
     * getter do menu
     * @return
     */
    Menu getCurrentMenu();

    /**
     * imprime a line no ecra
     * @param line
     */
    void showLine(String line);


    /**
     *setter para o timeQueue
     * @param timeQueue
     */
    void setTimeQueue(double timeQueue);


    /**
     * converte os dados para string para serem visualizados
     * @param fichName
     * @param estatistica
     */
    void showInfoView(String fichName,IEstatisticas estatistica);

}
