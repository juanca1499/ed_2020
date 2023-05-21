package interfaces;

import ednolineal.ArbolExpresion;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;

/**
 * Contiene las pruebas para la obtención del resultado
 * de la expresión contenida en un TDA ArbolExpresion
 * realizando dicho proceso dos veces: una con un recorrido
 * en preorden y otra en postorden.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaEvaluacionArbol
{
    public static void main(String[] args)
    {
        SalidaEstandar.consola("Ingrese la expresión a evaluar: ");
        String expresion = EntradaEstandar.consolaCadena();
        ArbolExpresion arbolExpresion = new ArbolExpresion();
        arbolExpresion.infijaAArbol(expresion);
        SalidaEstandar.consola("El arbol contiene la expresión: " + expresion);
        SalidaEstandar.consola("\nResultado con recorrido en preorden: " +
                arbolExpresion.evaluarPreOrden());
        SalidaEstandar.consola("\nResultado con recorrido en postorden: " +
                arbolExpresion.evaluarPostOrden());
    }
}