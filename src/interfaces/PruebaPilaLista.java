package interfaces;

import edlineal.Pila;
import edlineal.PilaLista;
import entradasalida.SalidaEstandar;

/**
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaPilaLista
{
    public static void main(String[] args)
    {
        PilaLista pila = new PilaLista();

        SalidaEstandar.consola("\n\nPila 1:");
        SalidaEstandar.consola("\nAgregando datos a la Pila:\n");
        pila.push(10);
        pila.push(30);
        pila.push("Adios");
        pila.push(300);
        pila.imprimir();
        SalidaEstandar.consola("\nEliminando el tope: " + pila.pop());
        SalidaEstandar.consola("\n");
        pila.imprimir();
        SalidaEstandar.consola("\nRecuperando el tope de la Pila: " + pila.verTope());
        SalidaEstandar.consola("\n¿Está vacía?: " + pila.vacia());
        SalidaEstandar.consola("\n¿Está llena?: " + pila.llena());

        SalidaEstandar.consola("\n\nPila 2:");
        PilaLista pila2 = new PilaLista();
        SalidaEstandar.consola("\n¿Está vacía?: " + pila2.vacia());
    }
}
