package interfaces;

import edlineal.Cola;
import edlineal.ColaLista;
import entradasalida.SalidaEstandar;

/**
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaColaLista
{
    public static void main(String[] args)
    {
        ColaLista cola = new ColaLista();

        SalidaEstandar.consola("\n\nCola 1:");
        SalidaEstandar.consola("\nAgregando datos a la Cola:\n");
        cola.agregar(10);
        cola.agregar(30);
        cola.agregar("Hola");
        cola.agregar(300);
        cola.imprimir();
        SalidaEstandar.consola("\nEliminando el primer elemento insertado: " + cola.eliminar());
        SalidaEstandar.consola("\n");
        cola.imprimir();
        SalidaEstandar.consola("\n¿Está vacía?: " + cola.vacia());
        SalidaEstandar.consola("\n¿Está llena?: " + cola.llena());

        SalidaEstandar.consola("\n\nCola 2:");
        ColaLista cola2 = new ColaLista();
        SalidaEstandar.consola("\n¿Está vacía?: " + cola2.vacia());
    }
}
