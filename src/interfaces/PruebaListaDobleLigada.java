package interfaces;

import edlineal.Arreglo;
import edlineal.ListaDobleLigada;
import entradasalida.SalidaEstandar;

/**
 * Clase con las pruebas de la clase ListaDobleLigada.
 * @version 1.0
 * @author Clase de estructura de datos.
 */
public class PruebaListaDobleLigada
{
    public static void main(String[] args)
    {
        ListaDobleLigada listaDoble = new ListaDobleLigada();


        SalidaEstandar.consola("\nInsertando 'A' al inicio: " + listaDoble.insertarInicio("A"));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nInsertando 'B' al inicio: " + listaDoble.insertarInicio("B"));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nAgregando 'A' al inicio: " + listaDoble.insertarInicio("A"));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nAgregando 'C': " + listaDoble.agregar("C"));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nImprimiendo al revés:\n");
        listaDoble.imprimirR();
        SalidaEstandar.consola("\n\nImprimiendo normal:\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nBuscando B: " + listaDoble.buscarDesdeFinal("B"));
        SalidaEstandar.consola("\n\nCambiando A por Z dos veces: " + listaDoble.setElemento("A","Z",2));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nPosiciones del elemento 'Z': \n");
        Arreglo ocurrencias = listaDoble.buscarOcurrencias("Z");
        ocurrencias.imprimir();
        SalidaEstandar.consola("\nInvirtiendo la Lista:\n");
        listaDoble.invertir();
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nOcurrencias del valor 'Z' en la lista: " + listaDoble.contar("Z"));
        SalidaEstandar.consola("\n\nInvirtiendo la Lista (de nuevo):\n");
        listaDoble.invertir();
        listaDoble.imprimir();

        SalidaEstandar.consola("\n\nBorrando el tope: " + listaDoble.eliminarTope());
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n");
        listaDoble.imprimirR();

        SalidaEstandar.consola("\n\nAgregando 'X': " + listaDoble.agregar("X"));
        SalidaEstandar.consola("\nAgregando 'Y': " + listaDoble.agregar("Y"));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nBorrando 'B': " + listaDoble.eliminar("B"));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n");
        listaDoble.imprimirR();

        SalidaEstandar.consola("\n\n¿Está llena la lista? " + listaDoble.llena());
        SalidaEstandar.consola("\n¿Está vacía la lista? " + listaDoble.vacia());
        SalidaEstandar.consola("\nBuscando el valor 'Z' en la lista: " + listaDoble.buscar("Z"));
        SalidaEstandar.consola("\nObteniendo el tope de la Lista: " + listaDoble.getTope());

        Arreglo arreglo = new Arreglo(3);
        arreglo.agregar("F");
        arreglo.agregar("G");
        arreglo.agregar("H");
        SalidaEstandar.consola("\n\nAgregado el arreglo a la lista: " + listaDoble.agregarArreglo(arreglo));
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();
        SalidaEstandar.consola("\n\nRellenando 3 veces con 'R':");
        listaDoble.rellenar("R",3);
        SalidaEstandar.consola("\n");
        listaDoble.imprimir();

        SalidaEstandar.consola("\n\nIterando los elementos de la lista desde el principio:");
        listaDoble.inicializarIteradorPrincipio();
        while (listaDoble.hayNodo())
        {
            SalidaEstandar.consola(" " + listaDoble.obtenerSiguiente() + " ");
        }

        SalidaEstandar.consola("\nIterando los elementos de la lista desde el final:");
        listaDoble.inicializarIteradorFinal();
        while (listaDoble.hayNodo())
        {
            SalidaEstandar.consola(" " + listaDoble.obtenerAnterior() + " ");
        }

        SalidaEstandar.consola("\nRecorremos dos posiciones la lista y vamos dos atrás: \n");
        listaDoble.inicializarIteradorPrincipio();
        String cadena = ""+ listaDoble.obtenerSiguiente() + " " + listaDoble.obtenerSiguiente() +
                " ";
        listaDoble.obtenerAnterior();
        cadena += listaDoble.obtenerAnterior();
        SalidaEstandar.consola(cadena);

        SalidaEstandar.consola("\n\nVACIANDO LA LISTA :'(\n");
        listaDoble.vaciar();
        listaDoble.imprimir();








    }
}
