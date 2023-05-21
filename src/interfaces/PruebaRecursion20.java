package interfaces;

import entradasalida.SalidaEstandar;
import matematicas.Matematica;
import matematicas.SucesionNum;
import utilidades.ConversorBaseNum;

/**
 * Contiene las pruebas de funciones recursivas.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaRecursion20
{
    public static void main(String[] args)
    {
        SalidaEstandar.consola("El MCD de 412 y 184 es: " + Matematica.maxComunDivisor(412, 184));
        SalidaEstandar.consola("\nEquivalente de 150 en binario: " + ConversorBaseNum.decimalABinario(150));
        SalidaEstandar.consola("\n65029 a Hexadecimal: " + ConversorBaseNum.decimalAHexadecimal(65029));
        SalidaEstandar.consola("\n2253 a Octal: " + ConversorBaseNum.decimalABaseN(2253, 8));
        SalidaEstandar.consola("\nSucesionX con el valor 12, potencia 15 y divisor 30 = " +
                SucesionNum.sucesionX(12, 15, 30));

    }
}
