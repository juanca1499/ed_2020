package utilidades;

/**
 * Este enum define el TDA TipoCorrelacion.
 * Contiene los tipos de correlación existentes.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum TipoCorrelacion
{

    /** Define un tipo de correlación que es nula: 0. */
    NINGUNA(0, "Ninguna correlación"),
    /** Define un tipo de correlación positiva perfecta: 1. */
    POSITIVA_PERFECTA(1, "Positiva perfecta"),
    /** Define un tipo de correlación positiva, pero no perfecta. */
    POSITIVA(2, "Positiva"),
    /** Define un tipo de correlación negativa perfecta: -1. */
    NEGATIVA_PERFECTA(3, "Negativa perfecta"),
    /** Define un tipo de correlación negativa, pero no perfecta. */
    NEGATIVA(3, "Negativa"),
    /** Define un tipo de correlación que no existe. */
    INVALIDA(4, "Invalida");

    /** Guarda el identificador del tipo de correlación. */
    private int id;
    /** Guarda el nombre del tipo de la correlación. */
    private String nombre;

    /**
     * Define el constructor de TDA TipoCorrelacion.
     * @param id Identificador del TipoCorrelacion.
     * @param nombre Nombre del TipoCorrelacion.
     */
    private TipoCorrelacion(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Recupera el identifiacador del tipo de correlación.
     * @return Regresa el id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Recupera el nombre del tipo de correlación.
     * @return Regresa el nombre.
     */
    public String getNombre()
    {
        return nombre;
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
