package interfaces;

import ednolineal.ArbolBinarioBusqueda;
import entradasalida.SalidaEstandar;

/**
 * Contiene las pruebas para la inserción y
 * búsqueda de elementos en un TDA ArbolBinarioBusqueda.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaArbolBinarioBusqueda
{
    public static void main(String[] args)
    {
        ArbolBinarioBusqueda arbolBB = new ArbolBinarioBusqueda();
        arbolBB.insertar(5);
        arbolBB.insertar(10);
        arbolBB.insertar(3);
        arbolBB.insertar(7);
        arbolBB.insertar(12);

        /*
                        5
                3               10
                    7               12
         */

        SalidaEstandar.consola("\n");
        arbolBB.inOrden();
        SalidaEstandar.consola("\n");

        SalidaEstandar.consola("Buscando el 10: " + arbolBB.buscar(10));
        SalidaEstandar.consola("\n");

        SalidaEstandar.consola("Buscando el 200: " + arbolBB.buscar(200));
        SalidaEstandar.consola("\n");

        SalidaEstandar.consola("Buscando el 5: " + arbolBB.buscar(5));
        SalidaEstandar.consola("\n");
    }
}
