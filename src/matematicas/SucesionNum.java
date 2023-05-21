package matematicas;

/**
 * Esta clase implementa el TDA SucesionNum.
 * Contiene calculos de sucesiones numéricas.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class SucesionNum
{

    /**
     * Calcula la sucesioX de un número.
     * @param constante Número constante a calcular su sucesión.
     * @param maxPotencia Potencia máxima a la que llegará el cálculo
     *                    de la sucesión.
     * @param maxDivisor Divisor máximo al que llegará el cálculo de
     *                   la sucesión.
     * @return Regresa el resultado de la sucesión.
     */
    public static double sucesionX(int constante, int maxPotencia, int maxDivisor)
    {
        return (1 - 1.5) + sucesionX(constante, 1, maxPotencia, 5, maxDivisor);
    }

    /**
     * Función real que calcula la sucesion de forma recursiva.
     * @param constante Número constante a calcular su sucesión.
     * @param pot Potencia actual a la que se está elevando la constante.
     * @param maxPot Potencia máxima a la que llegará el cálculo
     *               de la sucesión.
     * @param div Divisor actual entre el cual se está dividiendo la
     *            contante.
     * @param maxDiv Divisor máximo al que llegará el cálculo de
     *               la sucesión.
     * @return Regresa el resultado de la parte recursiva de la sucesión.
     */
    private static double sucesionX(int constante, int pot, int maxPot, int div, int maxDiv)
    {
        // Caso base.
        if(pot > maxPot || div > maxDiv)
        {
            return (Matematica.pow(constante, pot) / Matematica.factorial(div)) +
                    (pot / Matematica.factorial(div));
        }
        // Caso recursivo.
        else
        {
            // El divisor es par.
            if(div % 2 == 0)
            {
                return Matematica.pow(constante, pot) / Matematica.factorial(div) +
                        sucesionX(constante, pot + 2, maxPot, div + 5, maxDiv);
            }
            // El divisor es impar.
            else
            {
                return Matematica.pow(constante, pot) / Matematica.factorial(div) -
                        sucesionX(constante, pot + 2, maxPot, div + 5, maxDiv);
            }
        }
    }
}
