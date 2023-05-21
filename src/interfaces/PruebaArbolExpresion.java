package interfaces;

import ednolineal.ArbolBinario;
import ednolineal.ArbolExpresion;
import entradasalida.SalidaEstandar;

import javax.swing.tree.TreePath;


/**
 * Contiene las pruebas para la construccion
 * y manipulación de TDA's ArbolExpresion.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaArbolExpresion
{
    public static void main(String[] args)
    {
        ArbolExpresion arbol = new ArbolExpresion();
        arbol.crearArbol();
        SalidaEstandar.consola("\n\nLista de operadores: \n");
        arbol.getOperadores().imprimir();
        SalidaEstandar.consola("\n\nLista de operandos: \n");
        arbol.getOperandos().imprimir();
        ArbolExpresion arbolSinVariables = arbol.reemplazarVariables();

        SalidaEstandar.consola("\n\nRecorridos del Arbol con variables sin sustituir: \n");
        arbol.preOrden();
        SalidaEstandar.consola("\n");
        arbol.postOrden();
        SalidaEstandar.consola("\n");
        arbol.inOrden();
        SalidaEstandar.consola("\nCantidad de nodos padre: " + arbol.getCantNodosPadre());
        SalidaEstandar.consola("\nCantidad de nodos hoja: " + arbol.getCantNodosHoja());
        SalidaEstandar.consola("\nAltura del arbol: " + arbol.getAltura());

        SalidaEstandar.consola("\n\nRecorridos del arbol con las variables sustituídas: \n");
        arbolSinVariables.preOrden();
        SalidaEstandar.consola("\n");
        arbolSinVariables.postOrden();
        SalidaEstandar.consola("\n");
        arbolSinVariables.inOrden();
        SalidaEstandar.consola("\nCantidad de nodos padre: " + arbolSinVariables.getCantNodosPadre());
        SalidaEstandar.consola("\nCantidad de nodos hoja: " + arbolSinVariables.getCantNodosHoja());
        SalidaEstandar.consola("\nAltura del arbol: " + arbolSinVariables.getAltura());
    }
}
