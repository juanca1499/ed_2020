package utilidades;

/**
 * Define los distintos tipos de
 * recorridos que se pueden hacer
 * para visitar todos los vértices que
 * contiene un grafo o encontrar un vértice
 * específico.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo
 * @version 1.0.
 */
public enum RecorridoGrafo
{
    /** Define un recorrido completo o búsuqeda de un vértice en un grafo en
     * formato de profundidad, usando el algoritmo Depth First Search. */
    DFS(1, "Depth First Search"),
    /** Define un recorrido completo o búsuqeda de un vértice en un grafo en
     * formato de anchura, usando el algoritmo Breath First Search. */
    BFS(2, "Breath First Search");

    /** Guarda el identificador del tipo de recorrido del grafo. */
    private int id;
    /** Guarda el nombre del tipo de recorrido del grafo. */
    private String tipo;

    /**
     * Define el constructor enum RecorridoGrafo.
     * @param id Identificador a ser asignado al tipo de
     *           recorrido.
     * @param tipo Nombre a ser asignado al tipo de
     *             recorrido.
     */
    private RecorridoGrafo(int id, String tipo)
    {
        this.id = id;
        this.tipo = tipo;
    }

    /**
     * Asigna un identificador al tipo de recorrido
     * de un grafo.
     * @param id Identificador a ser asignado.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Recupera el identificador del tipo de recorrido
     * de un grafo.
     * @return Regresa el campo id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Asigna un nombre al tipo de recorrido sobre
     * un grafo.
     * @param tipo Nombre a ser asignado.
     */
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    /**
     * Recupera el nombre del tipo de recorrido de
     * un grafo.
     * @return Regresa el campo nombre.
     */
    public String getTipo()
    {
        return tipo;
    }
}
