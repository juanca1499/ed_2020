package interfaces;

import edlineal.ListaEnlazadaIndexada;
import entradasalida.SalidaEstandar;

/**
 * Clase con las pruebas de la clase ListaDobleIndexada.
 * @version 1.0
 * @author Clase de estructura de datos.
 */
public class PruebaListaDobleIndexada
{
    public static void main(String[] args)
    {
        ListaEnlazadaIndexada lista = new ListaEnlazadaIndexada();

        SalidaEstandar.consola("\nInsertando: \n");
        lista.insertar(0, "A");
        lista.insertar(1, "B");
        lista.insertar(2, "C");
        lista.insertar(3, "D");
        lista.insertar(4, "E");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nInsertando 'AA' en la Posicion 0:  \n");
        lista.insertar(0,"AA");
        lista.imprimirConIndex();


        SalidaEstandar.consola("\n\nInsertando 'BB' en la Posicion 2:  \n");
        lista.insertar(2,"BB");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nInsertando 'EE' en la Posicion 4:  \n");
        lista.insertar(4,"EE");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nElementos contenidos en la Lista.: " + lista.getSize());

        SalidaEstandar.consola("\n\nInsertando 'CC' en la Posicion -2:  \n");
        lista.insertar(-2,"CC");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nBorrando la posición 2:" + lista.eliminar(2));
        SalidaEstandar.consola("\n");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nBorrando la posición 0:" + lista.eliminar(0));
        SalidaEstandar.consola("\n");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nBorrando la posición 6:" + lista.eliminar(6));
        SalidaEstandar.consola("\n");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nBorrando la posición -2:" + lista.eliminar(-2));
        SalidaEstandar.consola("\n");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nObteniendo la posición 2: " + lista.getElemeto(2));
        SalidaEstandar.consola("\n");

        SalidaEstandar.consola("\nCambiando la posición 2 por FF: " + lista.setElemento(2, "FF"));
        SalidaEstandar.consola("\n");
        lista.imprimirConIndex();

        SalidaEstandar.consola("\n\nImprimiendo sin índices:\n");
        lista.imprimir();

















    }
}
