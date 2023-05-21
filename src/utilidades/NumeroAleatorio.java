package utilidades;

import java.util.Random;

/**
 * Esta clase implementa el TDA NumeroAleatorio.
 * Extiende la funcionalidad de la clase Random
 * de Java con la posibilidad de generar números
 * aleatorios entre un rango de números.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class NumeroAleatorio
{
    /** Guarda el generador de números aleatorios. */
    private Random random;

    /**
     * Define el constructor TDA NumeroAleatorio.
     */
    public NumeroAleatorio()
    {
        random = new Random();
    }

    /**
     * Genera un número aleatorio de tipo double
     * entre un rango definido.
     * @param min Límite inferior del número a generar.
     * @param max Límite superior del número a generar.
     * @return Regresa el número generado.
     */
    public double generarDouble(double min, double max)
    {
        double numero = min + (max - min) * random.nextDouble();
        return numero;
    }
}

