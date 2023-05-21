package interfaces;

import ednolineal.ArbolBinario;
import entradasalida.SalidaEstandar;

/**
 * Contiene las pruebas para la construccion
 * y manipulacion de un TDA ArbolBinario
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaArbol
{
    public static void main(String[] args)
    {
        ArbolBinario arbol = new ArbolBinario();
        arbol.crearArbol();

        /*
            Árbol de muestra
                              f
                b                       g
          a          d                      i
                 c       e              h

         */
        arbol.preOrden();
        SalidaEstandar.consola("\n");
        arbol.postOrden();
        SalidaEstandar.consola("\n");
        arbol.inOrden();
        SalidaEstandar.consola("\n");
    }
}
