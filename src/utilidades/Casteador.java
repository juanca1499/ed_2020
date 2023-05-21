package utilidades;

import edlineal.Arreglo;

/**
 * Esta clase implementa el TDA Casteador.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Casteador
{
    /**
     * Convierte un arreglo de tipo double como dato
     * primitivo, a un arreglo de Objects.
     * @param array Arreglo primitivo a ser convertido.
     * @return Regresa un arreglo de Objects con los mismos
     * datos que tenía array.
     */
    public static Object[] convertirPrimitivo(double[] array)
    {
       Object[] objetos = new Object[array.length];

       for(int contador = 0; contador < array.length; contador++)
       {
           objetos[contador] = array[contador];
       }
       return objetos;
    }

    /**
     * Convierte un arreglo de Objects, a un arreglo de
     * tipo double como dato primitivo.
     * @param array Arreglo de Objects a ser convertido.
     * @return Regresa un arreglo de tipo double primitivo
     * con los mismos datos que tenía array.
     */
    public static double[] convertirDoublePrimitivo(Object[] array)
    {
       double[] datos = new double[array.length];

       for(int contador = 0; contador < array.length - 1; contador++)
       {
           datos[contador] = (double)array[contador];
       }
       return datos;
    }

    /**
     * Convierte los datos del Arreglo a un
     * arreglo primitivo de double
     * @param entrada Arreglo a ser convertido
     * @return Regresa un arreglo de double
     * con los datos convertidos.
     */
    public static double[] convertirDoublePrimitivo(Arreglo entrada)
    {
        double[] resultado = new double[entrada.getLongitud()];

        for(int index = 0; index < entrada.getLongitud(); index++)
        {
            resultado[index] = (double)entrada.getElemento(index);
        }
        return resultado;
    }
}
