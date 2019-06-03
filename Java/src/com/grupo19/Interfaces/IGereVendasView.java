package com.grupo19.Interfaces;

import com.grupo19.Menu;

import java.util.List;

public interface IGereVendasView {
    void navigate();
    void updateView ();
    void updateMenu(Menu menu);
    void setRow(int row);
    void setCol(int col);
    void addToStringBrowser (List<String> stringList );
    int getChoice();
    boolean exit();
    Menu getCurrentMenu();
    void showLine(String line);
    boolean choiceIsOutOfRange();
}
