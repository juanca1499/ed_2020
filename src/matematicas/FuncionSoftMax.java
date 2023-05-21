package matematicas;

import edlineal.Matriz;
import edlineal.MatrizNum;
import edlineal.VectorNum;

/**
 * Esta clase implementa el TDA FuncionSoftMax.
 * Define los comportamientos específicos de este
 * tipo de función sobre una red neuronal.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class FuncionSoftMax
{
    /**
     * Genera una matriz con las derivadas de las últimas
     * salidas de la capa.
     * @param ultimasSalidas Vector con los últimos
     *                       resultados generados por
     *                       la capa.
     * @param targets Vector de resultados esperados
     *                de la red.
     * @return Regresa una matriz con la diagonal principal
     * rellena de las derivadas obtenidas y las demás posiciones
     * en ceros.
    */
    public static MatrizNum calcularDerivadas(VectorNum ultimasSalidas, VectorNum targets)
    {
        MatrizNum salidasDerivadas = new MatrizNum(
        ultimasSalidas.capacidad(), ultimasSalidas.capacidad());

        // Se llena la diagonal principal de la matriz con
        // las derivadas de las salidas de la capa.
        for(int renglon = 0; renglon < ultimasSalidas.capacidad(); renglon++)
        {
            double resta = derivar((double)ultimasSalidas.getElemento(renglon),
            (double)targets.getElemento(renglon));
            salidasDerivadas.setElemento(renglon, renglon, resta);
        }
        return salidasDerivadas;
    }

    /**
     * Calcula la derivada de la función.
     * @param valorSalida Elemento del vector de salidas.
     * @param valorTarget Elemento del vector de resultados
     *                    esperados.
     * @return Regresa el resultado de la derivación.
    */
    public static double derivar(double valorSalida,
                                 double valorTarget)
    {
       return valorSalida - valorTarget;
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
        // Se calcula el común denominador de la función.
        double denominador = sumatoria(entradas);

        // Se itera el vector de entradas.
        for(int index = 0; index < entradas.getLongitud(); index++)
        {
            // Se aplica la función de euler a cada elemento y se
            // divide entre el común denominador.
            double resultado = Math.exp(Double.parseDouble(entradas.
            getElemento(index).toString())) / denominador;
            // Se agrega el resultado.
            resultados.agregar(resultado);
        }
        return resultados;
    }

    /**
     * Calcula el denominador de la
     * función.
     * @param valores Elementos de entrada.
     * @return Regresa el resultado de la
     * sumatoria.
     */
    private static double sumatoria(VectorNum valores)
    {
        double resultado = 0.0;
        // Se itera el vector.
        for(int index = 0; index < valores.getLongitud(); index++)
        {
            // Se aplica la función de euler a cada elemento y se
            // suman los resultados.
            resultado += Math.exp(Double.parseDouble(
            valores.getElemento(index).toString()));
        }
        return resultado;
    }
}
