package com.grupo19;

public enum Menu {
    MAINMENU(new String[] {"Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o seu respectivo total",
            "Dado um mês , determinar o número total global de vendas realizadas e o número total de clientes distintos que as fizeram.",
            "Dado um código de cliente, determinar, para cada mês, quantas compras fez, quantos produtos distintos comprou e quanto gastou no total.",
            "Dado o código de um produto existente, quantas vezes foi comprado, por quantos clientes diferentes e o total facturado.",
            "Dado o código de um cliente determinar os produtos que mais comprou",
            "Determinar X produtos mais vendidos em todo o ano ",
            "Determinar a lista dos três maiores compradores em termos de dinheiro facturado, por filial",
            "Determinar os códigos dos X clientes que compraram mais produtos diferentes",
            "Dado o código de um produto que deve existir, determinar o conjunto dos X clientes que mais o compraram  ",
            "Determinar mês a mês, e para cada mês filial a filial, a facturação total do produto dado"}),
    STRINGBROWSER;

    private String[] menuOptions;
    Menu() {

    }

    /**
     * construtor parametrizado
     * @param menuOptions
     */
    Menu(String[] menuOptions) {
         this.menuOptions = menuOptions;
    }

    /**
     * metodo getter da String de opcoes do menu
     * @return
     */
    public String[] getMenuOptions ( ) {
        return menuOptions;
    }
}
