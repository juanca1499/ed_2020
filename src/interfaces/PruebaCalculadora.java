package interfaces;

import entradasalida.SalidaEstandar;
import matematicas.Calculadora;
import matematicas.ExpresionAritmetica;


public class PruebaCalculadora
{
    public static void main(String[] args)
    {


        //SalidaEstandar.consola("\nResolviendo con postfija...\n");
        String expresion = Calculadora.pedirExpresion();
        String expresionSustituida = Calculadora.detectarVariables(expresion);
        String expresionPostFija = ExpresionAritmetica.infijaAPostFija(expresionSustituida);
        String expresionPreFija = ExpresionAritmetica.infijaAPreFija(expresionSustituida);
        SalidaEstandar.consola("\nExpresion sustituida: " + expresionSustituida);
        SalidaEstandar.consola("\nExpresion postfija: " + expresionPostFija);
        SalidaEstandar.consola("\nExpresion prefija: " + expresionPreFija);
        //SalidaEstandar.consola("\nEl resultado de la operación es: " +
          //      Calculadora.evaluarExpresionPostFija(expresionPostFija));

        /**
        SalidaEstandar.consola("\nResolviendo con prefija...\n");
        String expresionPreFija = ExpresionAritmetica.infijaAPreFija(expresionSustituida);
        SalidaEstandar.consola("\nExpresion prefija: " + expresionPreFija);
        SalidaEstandar.consola("\nEl resultado de la operación es: " +
                Calculadora.evaluarExpresionPreFija(expresionPreFija));
         */
    }
}
