package catalogos;

/**
 * Esta clase define el flujo de una arista en un
 * grafo. Indica del vértice que parte y al que llega.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class Arista
{
    /** Guarda el vértice del que parte la arista. */
    private Vertice origen;
    /** Guarda el vértice al que llega la arista. */
    private Vertice destino;
    /** Guarda el costo que genera recorrer la arista. */
    private double peso;

    /**
     * Define el constructor TDA Arista.
     */
    public Arista()
    {
       origen = null;
       destino = null;
    }

    /**
     * Define el constructor TDA Arista.
     * @param origen Vértice de partida de la arista.
     * @param destino Vértice de llegada de la arista
     */
    public Arista(Vertice origen, Vertice destino)
    {
        this.origen = origen;
        this.destino = destino;
    }

    /**
     * Define el constructor TDA Arista.
     * @param origen Vértice de partida de la arista.
     * @param destino Vértice de llegada de la arista
     * @param peso Costo por recorrer la arista.
     */
    public Arista(Vertice origen, Vertice destino, double peso)
    {
       this.origen = origen;
       this.destino = destino;
       this.peso = peso;
    }

    /**
     * Asigna el vértice de partida de la arista.
     * @param origen Vértice a fungir como el inicio
     *               de la arista.
     */
    public void setOrigen(Vertice origen)
    {
        this.origen = origen;
    }

    /**
     * Obtiene el vértice de partida de la arista.
     * @return Regresa el campo origen.
     */
    public Vertice getOrigen()
    {
        return origen;
    }

    /**
     * Asigna el vértice de llegada de la arista.
     * @param destino Vertice a fungir como el final
     *                de la arista.
     */
    public void setDestino(Vertice destino)
    {
        this.destino = destino;
    }

    /**
     * Obtiene el vértice de llegada de la arista.
     * @return Regresa el campo destino.
     */
    public Vertice getDestino()
    {
        return destino;
    }

    /**
     * Asigna el costo que se tiene al recorrer
     * la arista.
     * @param peso Valor a ser asignado.
     */
    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    /**
     * Obtiene el peso que implica recorrer
     * la arista.
     * @return Regresa el campo peso.
     */
    public double getPeso()
    {
        return peso;
    }

    @Override
    public String toString()
    {
        return "(" + origen.toString() + "," + destino.toString() +
        "):" + peso;
    }
}
