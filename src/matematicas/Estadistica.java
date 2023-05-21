package matematicas;


import edlineal.Lista;
import edlineal.ListaDobleLigada;
import edlineal.VectorNum;
import entradasalida.SalidaEstandar;

/**
 * Esta clase implementa el TDA Estadistica.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Estadistica
{
    /**
     * Recorre todos los valores contenidos en el
     * vector para obtener el promedio de los mismos.
     * @param vector Datos para obtener el promedio.
     * @return Regresa el promedio de los elementos.
     */
    public static double promedio(VectorNum vector)
    {
        if(vector != null)
        {
            double promedio = 0;

            for (int contador = 0; contador <= vector.getLongitud() - 1; contador++)
            {
                promedio += Double.parseDouble(vector.getElemento(contador).toString());
            }
            promedio = promedio / (vector.getLongitud() - 1);
            return promedio;
        }
        return 0;
    }

    /**
     * Realiza la suma de los valores contenidos en
     * el vector hasta la posición indicada.
     * @param vector Datos a ser sumados.
     * @param cuantos Posición del vector hasta donde
     *                se realizará la suma.
     * @return Regresa la suma de los valores indicados.
     */
    public static double suma(VectorNum vector, int cuantos)
    {
        double suma = 0;
        int contador = 0;

        while (vector != null && !vector.vacia() && vector.getElemento(contador) != null && cuantos > 0)
        {
            suma += Double.parseDouble(vector.getElemento(contador).toString());
            contador++;
            cuantos--;
        }
        return suma;
    }

    /**
     * Realiza la suma de los valores contenidos en
     * la Lista.
     * @param lista Datos a ser sumados.
     * @return Regresa la suma de los valores indicados.
     */
    public static double suma(ListaDobleLigada lista)
    {
        double suma = 0.0;

        lista.inicializarIteradorPrincipio();
        // Se itera la lista hasta que quede vacía.
        while (lista.hayNodo())
        {
            suma += Double.parseDouble(lista.obtenerSiguiente().toString());
        }
        return suma;
    }

    /**
     * Realiza la suma de los valores de la Lista
     * elevados a una potencia.
     * @param lista Datos a ser sumados.
     * @param potencia Potencia a elevar los datos.
     * @return Regresa la suma de los valores indicados.
     */
    public static double sumaPotencia(ListaDobleLigada lista, int potencia)
    {
        double suma = 0.0;

        lista.inicializarIteradorPrincipio();
        // Se realiza la suma.
        while (lista.hayNodo())
        {
            suma += Math.pow(Double.parseDouble(lista.obtenerSiguiente().toString()) , 2);
        }
        return suma;
    }

    /**
     * Multiplica el primer elemento de la primera Lista con el
     * primero de la segunda, así con cada par de elementos de las
     * Listas y suma los resultados de cada multiplicación.
     * @param lista1 Primer conjunto de datos.
     * @param lista2 Segundo conjunto de datos.
     * @return Regresa la suma de las multiplicaciones de las dos Listas.
     */
    public static double sumaMultiplicativa(ListaDobleLigada lista1, ListaDobleLigada lista2)
    {
        double suma = 0.0;

        lista1.inicializarIteradorPrincipio();
        lista2.inicializarIteradorPrincipio();
        // Las dos listas tienen el mismo tamaño
        if(lista1.getSize() == lista2.getSize())
        {
            // Se realiza la suma.
            while (lista1.hayNodo())
            {
                suma += Double.parseDouble(lista1.obtenerSiguiente().toString()) *
                        Double.parseDouble(lista2.obtenerSiguiente().toString());
            }
        }
        return suma;
    }

    /**
     * Calcula la correlación de Pearson que hay entre dos conjuntos de datos.
     * Para que pueda haber correlación las dos listas tienen que
     * tener la misma longitud.
     * @param lista1 Primer conjunto de datos.
     * @param lista2 Segundo conjunto de datos.
     * @return Regresa la correlación de los dos conjuntos.
     */
    public static double correlacionPearson(ListaDobleLigada lista1, ListaDobleLigada lista2)
    {

        // Los dos conjuntos de datos tienen la misma longitud.
        if(lista1.getSize() == lista2.getSize())
        {
            // Cálculos que conforman la fórmula.
            int cantElementos = lista1.getSize();
            double sumaLista1 = suma(lista1);
            double sumaLista2 = suma(lista2);
            double sumaPotenciaL1 = sumaPotencia(lista1, 2);
            double sumaPotenciaL2 = sumaPotencia(lista2, 2);
            double sumaMultiplicativa = sumaMultiplicativa(lista1, lista2);

            // Operaciones del dividendo de la fórmula.
            double dividendo = (cantElementos * sumaMultiplicativa) - (sumaLista1) * (sumaLista2);

            // Operaciones del divisor de la fórmula.
            double factor1 = (cantElementos * sumaPotenciaL1) - Math.pow(sumaLista1, 2);
            double factor2 = (cantElementos * sumaPotenciaL2) - Math.pow(sumaLista2, 2);
            double divisor = Math.sqrt(factor1 * factor2);

            return dividendo / divisor;
        }
        // La longitud de los conjuntos no es igual.
        else
        {
            return 0.0;
        }
    }

    /**
     * Cuenta la cantidad de elementos del vector que son mayores
     * al promedio de los mismos.
     * @param vector Datos a ser comparados.
     * @return Regresa la cantidad de valores que fueron mayores
     * al promedio.
     */
    public static int mayoresDelPromedio(VectorNum vector)
    {
        int cantidadMayores = 0;

        if(vector != null)
        {
            double promedio = promedio(vector);

            for (int contador = 0; contador <= vector.getLongitud() - 1; contador++)
            {
                if(Double.parseDouble(vector.getElemento(contador).toString()) > promedio)
                {
                    cantidadMayores++;
                }
            }
        }
        return cantidadMayores;
    }
}
