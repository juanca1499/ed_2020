package interfaces;

import edlineal.Pila;
import entradasalida.SalidaEstandar;
import matematicas.ExpresionAritmetica;

public class PruebaPila
{
    public static void main(String[] args)
    {
        Pila pila1 = new Pila(5);

        pila1.push(45);
        pila1.push(1);
        pila1.push(3);
        pila1.push(5);
        pila1.push(7);
        pila1.push(5);
        SalidaEstandar.consola("\n");
        pila1.imprimir();
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("El tope es: " + pila1.verTope());
        //a+b*(c/d)^e
        //donde a = 1, b = 2, c = 2, d = 1, e = 2
        SalidaEstandar.consola("\nEjecutado la expresión posfija: "
        + ExpresionAritmetica.evaluarExpresionPostFija("1221/2^*+"));
        //abcd/e^*+
        SalidaEstandar.consola("\nEjecutado la expresión prefija: "
                + ExpresionAritmetica.evaluarExpresionPreFija("+1*2^/212"));


        SalidaEstandar.consola("\nImprimiendo pila de forma recursiva:\n");
    }
}
