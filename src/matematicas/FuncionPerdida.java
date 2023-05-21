package matematicas;

import edlineal.VectorNum;

/**
 * Esta clase implementa el TDA FuncionPérdida.
 * Contiene los procedimientos para calcular
 * el error que genera una red neuronal.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class FuncionPerdida
{
    /**
     * Calcula el nivel de error que se obtiene de
     * una red neuronal de predicción.
     * @param resultados Salidas de una red neuronal.
     * @param target Valores esperados de una red neuronal.
     * @return Regresa el resultado de la operación.
     */
    public static double perdidaLineal(VectorNum resultados, VectorNum target)
    {
        double perdida = 0.0;

        // Se iteran los dos vectores.
        for(int index = 0; index < resultados.getLongitud(); index++)
        {
            // Diferencia entre cada posición.
            double resta = (double)target.getElemento(index) -
            (double)resultados.getElemento(index);
            // Sumatoria de las diferencias.
            perdida += resta;
        }
        // Cálculo del promedio.
        return perdida / resultados.getLongitud();
    }

    /**
     * Calcula el nivel de error que se obtiene de
     * una red neuronal de categorización.
     * @param resultados Salidas de la red neuronal.
     * @param target Resultados esperados de la red.
     * @return Regresa el resultado de la operación.
     */
    public static double entropiaCruzada(VectorNum resultados, VectorNum target)
    {
        double perdida = 0.0;

        // Se iteran los vectores.
        for(int index = 0; index < resultados.getLongitud(); index++)
        {
            perdida += (double)target.getElemento(index) *
            Math.log((double)resultados.getElemento(index));
        }
        // Finalmente se multiplica por -1.
        return -1 * perdida;
    }
}
