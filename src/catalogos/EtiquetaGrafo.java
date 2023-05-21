package catalogos;

/**
 * Define un compuesto de elementos que son necesarios conocer
 * para llevar a cabo el algoritmo de Dijkstra sobre un grafo.
 * @author Clase de Estructura de Datos.
 * @version 1.0.
 */
public class EtiquetaGrafo
{
    /** Guarda el costo acumulado o <b>metrica acumulada</b> que se tiene por recorrer un camino del grafo. */
    protected double metricaA;
    /** Guarda el nodo del que proviene el camino actual. */
    protected int nodoAnterior;
    /** Guarda el número de iteración que se lleva en el algoritmo. */
    protected int iteracion;

    /**
     * Define el constructor TDA EtiquetaGrafo.
     */
    public EtiquetaGrafo() { }

    /**
     * Recupera la metrica acumulada de la etiqueta.
     * @return Regresa el campo metricaA.
     */
    public double getMetricaA()
    {
        return metricaA;
    }

    /**
     * Recupera el nodo anterior que contiene la etiqueta.
     * @return Regresa el campo nodoAnterior.
     */
    public int getNodoAnterior()
    {
        return nodoAnterior;
    }

    /**
     * Recupera la iteración que se tiene hasta el momento en la etiqueta.
     * @return Regresa el campo iteracion.
     */
    public int getIteracion()
    {
        return iteracion;
    }

    /**
     * Asigna la metrica acumulada a la etiqueta.
     * @param metricaA Valor a ser asignado como metrica
     *                 acumulada.
     */
    public void setMetricaA(double metricaA)
    {
        this.metricaA = metricaA;
    }

    /**
     * Asigna el nodo anterior de donde viene el camino recorrido
     * a la etiqueta.
     * @param nodoAnterior Valor a ser asignado como
     *                     nodo anterior.
     */
    public void setNodoAnterior(int nodoAnterior)
    {
        this.nodoAnterior = nodoAnterior;
    }

    /**
     * Asigna el número de iteración que se lleva hasta el momento
     * en el recorrido del grafo.
     * @param iteracion Valor a ser asignado como número de
     *                  iteración.
     */
    public void setIteracion(int iteracion)
    {
        this.iteracion = iteracion;
    }

    @Override
    public String toString()
    {
        return "[" + metricaA + "," + nodoAnterior + "]" + iteracion;
    }
}
