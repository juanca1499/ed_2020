package utilidades;

/**
 * Define los distintos tipos de
 * caminos que se pueden hacer
 * entre dos vértices de un TDA Grafo.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo
 * @version 1.0.
 */
public enum CaminoGrafo
{

    /**
     * Define un tipo de camino normal entre dos vértices de un grafo.
     * Este camino se caracteriza solamente porque haya una ruta válida
     * entre dos vértices.
     */
    NORMAL(1, "Normal"),
    /** Define un tipo de camino simple entre dos vértices de un grafo.
     * Este camino se caracteriza porque todos los vértices recorridos
     * tienen que ser distintos excepto el primero y el último.*/
    SIMPLE(2, "Simple"),
    /** Define un tipo de camino cerrado entre dos vértices de un grafo.
     * Este camino se caracteriza porque el primer vértice y el último sean
     * el iguales. */
    CERRADO(3, "Cerrado");

    /** Guarda el identificador del tipo de camino del grafo. */
    private int id;
    /** Guarda el nombre del tipo de camino del grafo. */
    private String tipo;

    /**
     * Define el constructor enum CaminoGrafo.
     * @param id Identificador a ser asignado al tipo de
     *           camino.
     * @param tipo Nombre a ser asignado al tipo de
     *             camino.
     */
    private CaminoGrafo(int id, String tipo)
    {
        this.id = id;
        this.tipo = tipo;
    }

    /**
     * Asigna un identificador al tipo de camino
     * de un grafo.
     * @param id Identificador a ser asignado.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Recupera el identificador del tipo de camino
     * de un grafo.
     * @return Regresa el campo id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Asigna un nombre al tipo de camino sobre
     * un grafo.
     * @param tipo Nombre a ser asignado.
     */
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    /**
     * Recupera el nombre del tipo de camino de
     * un grafo.
     * @return Regresa el campo nombre.
     */
    public String getTipo()
    {
        return tipo;
    }
}
