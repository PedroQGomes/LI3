package com.grupo19.Interfaces;

import java.util.List;

public interface ICatProd {

    /**
     * adiciona um produto ao catalogo de produtos
     * @param product
     */
    void add(IProduct product);


    /**
     * dá a lista de n tamanho com os produtos mais vendidos
     * @param n
     * @return
     */
    List<String> productsMostSell (int n);


    /**
     * verifica se um dado produto existe no catalogo de produtos
     * @param codProd
     * @return boolean
     */
    boolean contains(String codProd);


    /**
     * atualiza um produto que foi comprado numa dada filial numa certa quantidade
     * @param codProd
     * @param filial
     * @param qnt
     */
    void updateProductBought(String codProd , int filial , int qnt);


    /**
     * dá a lista de produtos que nunca foram comprados
     * @return neverBought
     */
    List<IProduct> productsNeverBought();
}
