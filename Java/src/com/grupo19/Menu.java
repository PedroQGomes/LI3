package com.grupo19;

public enum Menu {
    MAINMENU(new String[] {"Produtos Começados por uma determinada Letra",
            "Dado um mês e um código de produto, determinar e apresentar o número total de vendas e o total facturado com esse produto em tal mês",
            "Lista ordenada dos produtos que ninguém comprou",
            "Lista ordenada dos clientes que compraram em todas as filiais",
            "Determinar o número de clientes registados que não realizaram compras bem como o número de produtos que ninguém comprou",
            "Total de produtos comprados por um cliente(mês a mês)",
            "Determinar o total de vendas e o total facturado num intervalo de meses",
            "Determinar os códigos dos clientes que compraram um dado produto de uma dada filial",
            "Determinar a lista de códigos de produtos que um dado cliente mais comprou por quantidade(ordem decrescente)",
            "Produtos mais vendidos em todo o ano, indicando o nº de clientes e de unidades vendidas, filial a filial",
            "Determinar quais os códigos de 3 produtos em que um dado cliente mais gastou dinheiro durante o ano"}),
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
