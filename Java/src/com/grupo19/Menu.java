package com.grupo19;

public enum Menu {
    MAINMENU(new String[] {"Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o seu respectivo total",
            "Dado um mês , determinar o número total global de vendas realizadas e o número total de clientes distintos que as fizeram.", // PARA CADA FILIAL
            "Dado um código de cliente, determinar, para cada mês, quantas compras fez, quantos produtos distintos comprou e quanto gastou no total.",
            "Dado o código de um produto existente, quantas vezes foi comprado, por quantos clientes diferentes e o total facturado.", // Mês a Mês
            "Dado o código de um cliente determinar os produtos que mais comprou", //, ordenado por ordem decrescente de quantidade e, para quantidades iguais, por ordem alfabética dos códigos",
            "Determinar X produtos mais vendidos em todo o ano ", // (em número de unidades vendidas) indicando o número total de distintos clientes que o compraram (X é um inteiro dado pelo utilizador)
            "Determinar a lista dos três maiores compradores em termos de dinheiro facturado, por filial",
            "Determinar os códigos dos X clientes que compraram mais produtos diferentes", // (não interessa a quantidade nem o valor)  , indicando quantos, sendo o critério de ordenação a ordem decrescente do número de produtos
            "Dado o código de um produto que deve existir, determinar o conjunto dos X clientes que mais o compraram  ", // e, para cada um, qual o valor gasto (ordenação cf. 5);
            "Determinar mês a mês, e para cada mês filial a filial, a facturação total com cada produto"}),
    STRINGBROWSER;

    private String[] menuOptions;
    Menu() {

    }
    Menu(String[] menuOptions) {
         this.menuOptions = menuOptions;
    }

    public String[] getMenuOptions ( ) {
        return menuOptions;
    }
}
