package matematicas;


import edlineal.MatrizNum;
import edlineal.VectorNum;

/**
 * Esta clase implementa el TDA FuncionLineal.
 * Define los comportamientos específicos de este
 * tipo de función sobre una red neuronal.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class FuncionLineal
{
    /**
     * Genera una matriz con las derivadas de las últimas
     * salidas de la capa.
     * @param ultimasSalidas Vector con los últimos
     *                       resultados generados por
     *                       la capa.
     * @return Regresa una matriz con la diagonal principal
     * rellena de las derivadas obtenidas y las demás posiciones
     * en ceros.
     */
    public static MatrizNum calcularDerivadas(VectorNum ultimasSalidas)
    {
        MatrizNum salidasDerivadas = new MatrizNum(
        ultimasSalidas.capacidad(), ultimasSalidas.capacidad());

        // Se llena la diagonal principal de la matriz con
        // las derivadas de las salidas de la capa.
        for(int renglon = 0; renglon < ultimasSalidas.capacidad(); renglon++)
        {
            salidasDerivadas.setElemento(renglon, renglon,
            derivar((double)ultimasSalidas.getElemento(renglon)));
        }
        return salidasDerivadas;
    }


    /**
     * Calcula la derivada de la función.
     * @param valor Elemento a aplicarle
     *              la derivada.
     * @return Regresa el resultado
     * obtenido de la derivada.
     */
    public static double derivar(double valor)
    {
        return 1.0;
    }

    /**
     * Aplica la función a una serie
     * de valores.
     * @param entradas Valores a aplicarles
     *                 la función.
     * @return Regresa un vector con los
     * resultados de la función.
     */
    public static VectorNum calcular(VectorNum entradas)
    {
        VectorNum resultados = new VectorNum(entradas.getLongitud());

        // Se aplica el resultado de la función a cada una
        // de las entradas.
        for(int index = 0; index < entradas.getLongitud(); index++)
        {
            resultados.agregar(entradas.getElemento(index));
        }
        return resultados;
    }

    /**
     * Aplica la función para un sólo número.
     * @param var Variable independiente de
     *            la función.
     * @return Regresa el resultado de la función.
     */
    public static double calcular(double var)
    {
        return var;
    }
}
