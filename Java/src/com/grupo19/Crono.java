package com.grupo19;



/**
 *
 * @author asus
 */
/**
 * Crono = mede um tempo entre start() e stop()
 * O tempo e medido em nanosegundos e convertido para
 *  um double que representa os segs na sua parte inteira.
 *
 * @author FMM
 * @version (a version number or a date)
 */
import static java.lang.System.nanoTime;
public class Crono {

    private static long inicio = 0L;
    private static long fim = 0L;

    /**
     * inicia o contador
     */
    public static void start() {
        fim = 0L; inicio = nanoTime();
    }

    /**
     * termina o contador e devolve tempo passado em segundos
     * @return
     */
    public static double stop() {
        fim = nanoTime();
        long elapsedTime = fim - inicio;
        // segundos
        return elapsedTime / 1.0E09;
    }

    /**
     * imprime o stop()
     * @return
     */
    public static String print() {
        return "" + stop();
    }
}