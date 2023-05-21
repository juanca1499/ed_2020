package vistas;


import com.sun.corba.se.impl.oa.toa.TOA;
import edlineal.Arreglo;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;
import matematicas.ExpresionAritmetica;

/**
 * Esta clase implementa el TDA PantallaCalculadora.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PantallaCalculadora
{

    /**
     * Solicita al usuario por medio de la salida extandar una expresión
     * aritmética.
     * @return Regresa la expresión capturada.
     */
    public static String pedirExpresion()
    {
        SalidaEstandar.consola("Ingresa la expresión a evaluar: ");
        String expresion = EntradaEstandar.consolaCadena();
        return expresion;
    }

    /**
     * Solicita al usuario por medio de la salida estandar el
     * el valor que corresponde al nombre de variable dado como parámetro.
     * @param nombreVariable Nombre de variable por la cual se pregunta
     *                       su valor.
     * @return Regresa en forma de String el valor de la variable.
     */
    public static String pedirValorVariable(String nombreVariable)
    {
        SalidaEstandar.consola("\nIndica el valor de " + nombreVariable + ": ");
        String valor = EntradaEstandar.consolaCadena();
        return valor;
    }

}
