package interfaces;

import ednolineal.ArbolExpresion;
import entradasalida.SalidaEstandar;
import matematicas.ExpresionAritmetica;

/**
 * Contiene las pruebas para la construccion
 * de un ArbolExpresion a partir de una expresión
 * matemática infija, que en la encapsulación de
 * ArbolExpresion es convertirda a prefija o
 * postfija para crear el Arbol.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaArbolExpPreFija {

    public static void main(String[] args)
    {
        ArbolExpresion arbolExpresion = new ArbolExpresion();
        arbolExpresion.infijaAArbol("Z+B^(((C/D^R)^(E*F))/3)");
        SalidaEstandar.consola("Expresión matemática: Z+B^(((C/D^R)^(E*F))/3)");
        SalidaEstandar.consola("\nArbol preorden: ");
        arbolExpresion.preOrden();
        SalidaEstandar.consola("\nArbol inorden: ");
        arbolExpresion.inOrden();
        SalidaEstandar.consola("\nArbol postorden: ");
        arbolExpresion.postOrden();

        arbolExpresion = new ArbolExpresion();
        arbolExpresion.infijaAArbol("(a+b*(c/4))");
        SalidaEstandar.consola("\nArbol preorden: ");
        arbolExpresion.preOrden();
        SalidaEstandar.consola("\nArbol inorden: ");
        arbolExpresion.inOrden();
        SalidaEstandar.consola("\nArbol postorden: ");
        arbolExpresion.postOrden();
    }
}
