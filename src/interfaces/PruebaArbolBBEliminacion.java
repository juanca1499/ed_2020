package interfaces;


import ednolineal.ArbolBinarioBusqueda;
import entradasalida.SalidaEstandar;


/**
 * Contiene las pruebas para la eliminación de elementos
 * de un TDA ArbolBinarioBusqueda considerando los tres
 * posibles casos.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaArbolBBEliminacion
{
    public static void main(String[] args)
    {
        SalidaEstandar.consola("Eliminación en un árbol binario:");
        ArbolBinarioBusqueda arbolBB = new ArbolBinarioBusqueda();
        arbolBB.insertar(8);
        arbolBB.insertar(3);
        arbolBB.insertar(10);
        arbolBB.insertar(1);
        arbolBB.insertar(6);
        arbolBB.insertar(4);
        arbolBB.insertar(7);
        arbolBB.insertar(10);
        arbolBB.insertar(14);
        arbolBB.insertar(13);

        /*
                    8
            3               10
        1       6               14
             4      7       13
         */
        SalidaEstandar.consola("\n\n");
        SalidaEstandar.consola("Recorrido en inorden del árbol: ");
        arbolBB.inOrden();

        // Caso 1: Se elimina un nodo que tiene su hijo izquierdo y derecho.
        SalidaEstandar.consola("\n\n");
        SalidaEstandar.consola("Caso 1: Se elimina un nodo que tiene su hijo izquierdo y derecho.");
        SalidaEstandar.consola("\nEliminando la raíz 8: " + arbolBB.eliminar(8));
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("Recorrido en inorden del árbol: ");
        arbolBB.inOrden();

        // Caso 2: Se elimina un nodo que sólo tiene un hijo.
        SalidaEstandar.consola("\n\n");
        SalidaEstandar.consola("Caso 2: Se elimina un nodo que sólo tiene un hijo.");
        SalidaEstandar.consola("\nEliminando el 14: " + arbolBB.eliminar(14));
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("Recorrido en inorden del árbol: ");
        arbolBB.inOrden();

        // Caso 3: Se elimina un nodo hoja.
        SalidaEstandar.consola("\n\n");
        SalidaEstandar.consola("Caso 3: Se elimina un nodo hoja");
        SalidaEstandar.consola("\nEliminando el 1: " + arbolBB.eliminar(1));
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("Recorrido en inorden del árbol: ");
        arbolBB.inOrden();
    }
}
