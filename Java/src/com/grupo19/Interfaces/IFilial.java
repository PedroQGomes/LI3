package com.grupo19.Interfaces;

import com.grupo19.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFilial {

    /**
     * metodo que adiciona uma sala a filial
     * @param a sale a inserir
     */
    void add(ISale sale);

    /**
     * Método de clone da filial
     * @return clone de filial
     */
    IFilial clone();


    /**
     * metodo que da o total faturado por um cliente num dado mes
     * @param client String de cliente
     * @param month Int do mes
     * @return sum
     */
    double totalFaturadoPerClientPerMonth(String client,int month);

    /**
     * (query 3)metodo que determina os produtos distintos comprados por um cliente num dado mes
     *  e o numero de compras
     * @param cliente
     * @param mes
     * @return Tuple
     */
    ITuple<Integer, Set<String>> numOfDifferentProductsOfClientAndNumOfSales(String cliente, int mes);

    /**
     * (query 2) metodo que diz quantas vendas ouve num mes e quantos clintes distintos compraram
     * @param x
     * @return Tuple
     */
    ITuple<Integer,Integer> totalNumbOfSalesInMonthAndClientsBought(int x);



    /**
     * metodo que faz o get da filial completamente clonado
     * @return tmp
     */
    Map<String,List<List<ISale>>> getFilialData();

    /**
     * (query 5) lista de codigos dos produtos que comporu por ordem decrescente de quantidade
     * e para quantos iguais por ordem alfabetica
     * @param client
     * @return mapa
     */
    Map<String,Integer> getListOfProductsBoughtOfClient(String client);


    /**
     * (query 7)determinar a lista de tres maiores compradores em termos de dinheiro faturado
     * @return list<String>
     */
    List<String> getListOfClientsWhoMostBought();


    /**
     * (query 8)determinar os codigos dos X clientes(sendo X dado pelo utilizador) que compraram
     * mais produtos diferentes(nao interessa a quantidade nem o valor),indicado
     * quantos,sendo o criterio de ordenaçao a ordem decrescente do numero de produtos
     * @return mapa
     */
    Map<String,Set<String>> getClientsWhoBoughtMostOften();
}
