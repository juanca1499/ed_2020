package interfaces;

import ednolineal.ArbolBinario;
import entradasalida.SalidaEstandar;


/**
 * Contiene el recorrido e impresión de un ArbolBinario
 * por amplitud y la impresión y recorrido del mismo Arbol
 * en postorden inverso.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaArbolAmplitud
{
    public static void main(String[] args)
    {
        ArbolBinario arbol = new ArbolBinario();
        arbol.crearArbol();
        SalidaEstandar.consola("\nImpresión usando TDA Cola: ");
        arbol.imprimirPorAmplitud();
        SalidaEstandar.consola("\nImpresión usando TDA Pila: ");
        arbol.imprimirPostOrdenInverso();
    }
}
