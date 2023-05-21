package interfaces;

import edlineal.Arreglo;
import edlineal.ListaEnlazadaHash;
import edlineal.ListaLigada;
import edlineal.Matriz;
import entradasalida.SalidaEstandar;

/**
 * Clase con las pruebas de la clase ListaEnlazadaHash.
 * @version 1.0
 * @author Clase de estructura de datos.
 */
public class PruebaListaEnlazadaHash
{
    public static void main(String[] args)
    {
        ListaEnlazadaHash listaHash = new ListaEnlazadaHash();
        ListaEnlazadaHash listaVacia = new ListaEnlazadaHash();

        SalidaEstandar.consola("\n¿Está vacía la lista? " + listaHash.vacia());
        SalidaEstandar.consola("\n");
        listaHash.imprimir();

        SalidaEstandar.consola("\n\nInsertando (1)A: " + listaHash.insertar("1","A"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();
        SalidaEstandar.consola("\nInsertando (2)B: " + listaHash.insertar("2","B"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();
        SalidaEstandar.consola("\nInsertando (3)C: " + listaHash.insertar("3","C"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();
        SalidaEstandar.consola("\n\n¿Está vacía la lista? " + listaHash.vacia());

        SalidaEstandar.consola("\n\nInsertando (1)Z: " + listaHash.insertar("1","Z"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();

        SalidaEstandar.consola("\n\nEliminando la clave 3: " + listaHash.eliminar("3"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();
        SalidaEstandar.consola("\nEliminando el contenido B: " + listaHash.eliminarValor("B"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();

        SalidaEstandar.consola("\n\nInsertando (1)X: " + listaHash.insertar("2","X"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();
        SalidaEstandar.consola("\nInsertando (2)Y: " + listaHash.insertar("3","Y"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();
        SalidaEstandar.consola("\nInsertando (3)Z: " + listaHash.insertar("4","Z"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();

        SalidaEstandar.consola("\n\nBuscando la clave 2: " + listaHash.buscar("2"));
        SalidaEstandar.consola("\nBuscando el valor Y: " + listaHash.buscarValor("Y"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();

        SalidaEstandar.consola("\n\nSustituyendo en la clave 3 por H: " + listaHash.sustituir("3","H"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();
        SalidaEstandar.consola("\n\nSustituyendo el valor X por U: " + listaHash.sustituirValor("X","U"));
        SalidaEstandar.consola("\n");
        listaHash.imprimir();

        SalidaEstandar.consola("\n\nConvirtiendo la lista a Arreglos:");
        ListaLigada listaElementos = listaHash.aArreglos();
        Arreglo arregloValores = (Arreglo) listaElementos.eliminarTope();
        Arreglo arregloClaves = (Arreglo) listaElementos.eliminarTope();
        SalidaEstandar.consola("\nImprimiendo claves:\n");
        arregloClaves.imprimir();
        SalidaEstandar.consola("\nImprimiendo valores:\n");
        arregloValores.imprimir();

        SalidaEstandar.consola("\n\nConvirtiendo la lista a Listas:");
        listaElementos = listaHash.aListas();
        ListaLigada listaValores = (ListaLigada) listaElementos.eliminarTope();
        ListaLigada listaClaves = (ListaLigada) listaElementos.eliminarTope();
        SalidaEstandar.consola("\nImprimiendo claves:\n");
        listaClaves.imprimir();
        SalidaEstandar.consola("\nImprimiendo valores:\n");
        listaValores.imprimir();

        SalidaEstandar.consola("\n\nConvirtiendo la lista a Matriz:");
        Matriz matrizElementos = listaHash.aMatriz();
        SalidaEstandar.consola("\nImprimiendo la Matriz:\n");
        matrizElementos.imprimir();
    }
}
