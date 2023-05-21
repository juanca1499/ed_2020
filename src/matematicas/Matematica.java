package matematicas;

import edlineal.MatrizNum;

/**
 * Esta clase implementa el TDA Matematica.
 * Contiene múltiples cálculos o funciones comunes
 * en dicho campo de estudio.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Matematica
{

    /**
     * Calcula el máximo común divisor (MCD) de dos números.
     * @param num1 Primer número.
     * @param num2 Segundo número.
     * @return Regresa MCD encontrado entre los dos números, o -1 en
     * caso de que alguno de los números dados sea inválido: menor o igual a 0.
     */
    public static int maxComunDivisor(int num1, int num2)
    {
        // Si los dos números son válidos para obtener el MCD,
        // se invoca a la función que realiza dicho proceso.
        if(num1 >= 0 && num2 >= 0)
        {
            return maxComunDivisorR(num1, num2);
        }
        // Si uno de los números es inválido, no se realiza
        // el proceso.
        else
        {
            return -1;
        }
    }

    /**
     * Calcula el máximo común divisor (MCD) de dos números.
     * @param num1 Primer número.
     * @param num2 Segundo número.
     * @return Regresa MCD encontrado entre los dos números.
     */
    private static int maxComunDivisorR(int num1, int num2)
    {
        // Caso base: Los dos números son iguales.
        if(num1 == num2)
        {
            return num1;
        }
        // Casos recursivos: Los dos números no son iguales.
        else
        {
            // El primer número es mayor al segundo.
            if(num1 > num2)
            {
                return maxComunDivisorR(num1 - num2, num2);
            }
            // El segundo número es mayor al primero.
            else
            {
                return maxComunDivisorR(num1, num2 - num1);
            }
        }
    }

    /**
     * Calcula el factorial de un número.
     * @param numero Valor a obtener su factorial.
     * @return Regresa el resultado del factorial del
     * número.
     */
    public static double factorial(int numero)
    {
        // Caso base.
        if(numero == 1)
        {
            return 1;
        }
        // Caso recursivo.
        else
        {
            return numero * factorial(numero - 1);
        }
    }

    /**
     * Eleva un número a una potencia.
     * @param base Número a ser potenciado.
     * @param potencia Exponente a ser elevado.
     * @return Regresa el resultado de elevar el
     * número al exponente dado.
     */
    public static double pow(int base, int potencia)
    {
        // Caso base.
        if(potencia == 1)
        {
            return base;
        }
        // Caso recursivo.
        else
        {
            return base * pow(base, potencia - 1);
        }
    }
}
