package com.grupo19;
import com.grupo19.Models.Product;

import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class testeVendas {
    /**
     * metodo main
     * @param args
     */
    public static void main(String[] args) {
        Product product = new Product("AB1034");
        Product product1 = new Product("ABB103");
        Product product2 = new Product("A11034");
        Product product3 = new Product("A01034");
        Product product4 = new Product("ABBBBB");
        Product product5 = new Product("121034");
        out.println(product.isValid());
        out.println(product1.isValid());
        out.println(product2.isValid());
        out.println(product3.isValid());
        out.println(product4.isValid());
        out.println(product5.isValid());
       /* List<String> lines;
        Crono.start();
        lines = lerLinhasWithBuff("Vendas_1M.txt");
        out.println(Crono.stop());
        out.println(lines.size());
        out.println("SEGUNDO TESTE:");
        Crono.start();
        lerAllLines("Vendas_1M.txt");
        out.println(Crono.stop()); */
    }

    /**
     * le linha por linha de ficheiro para uma list de strings
     * @param fichtxt
     * @return
     */
    public static List<String> lerLinhasWithBuff (String fichtxt) {
        List<String> linhas = new ArrayList<>();
        BufferedReader inFile;
        String linha;
        try {
            inFile = new BufferedReader((new FileReader(fichtxt)));
            while((linha = inFile.readLine()) != null) linhas.add(linha);
        } catch(IOException exc) {
            out.println(exc);

        }
        return linhas;
    }

    /**
     * le o ficheiro de uma vez
     * @param fichtxt
     * @return
     */
    public static List<String> lerAllLines(String fichtxt) {
        List<String> linhas = new ArrayList<>();
        try {
            linhas = Files.readAllLines(Paths.get(fichtxt));
        } catch (IOException exc) {out.println(exc);}
        return linhas;
    }
}


