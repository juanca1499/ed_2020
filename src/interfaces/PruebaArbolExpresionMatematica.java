package interfaces;

import ednolineal.ArbolBinario;
import ednolineal.ArbolExpresion;
import entradasalida.SalidaEstandar;

/**
 * Contiene las pruebas para la creación de un
 * TDA ArbolExpresion a partir de una cadena de
 * texto que contiene una expresión matemática
 * priorizada por paréntesis.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaArbolExpresionMatematica
{
    public static void main(String[] args)
    {
        SalidaEstandar.consola("\nArbol 1:");
        ArbolBinario arbol = new ArbolExpresion("(((_Var1*Var2) + (va3r/3)) - (b+var10))");
        SalidaEstandar.consola("\nExpresión original: (((_Var1*Var2) + (va3r/3)) - (b+var10))");
        SalidaEstandar.consola("\nExpresión convertida a Árbol (preorden): ");
        arbol.preOrden();

        SalidaEstandar.consola("\n\nArbol 2:");
        arbol = new ArbolExpresion("(((a*c)+(e/g))-(b + d))");
        SalidaEstandar.consola("\nExpresión original: (((a*c)+(e/g))-(b + d))");
        SalidaEstandar.consola("\nExpresión convertida a Árbol (preorden): ");
        arbol.preOrden();
    }
}
