package utilidades;

/**
 * Este enum define el TDA TipoImpresion.
 * Define el nivel de detalle en que se imprimirán
 * los datos de un objeto.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum TipoImpresion
{
    /** Define un tipo de impresión profunda o detallada. */
    DETALLADA(1, "Detallada"),
    /** Define un tipo de impresión básica o no detallada */
    NO_DETALLADA(2, "No Detallada");

    /** Guarda el id del TipoImpresion */
    private int id;
    /** Guarda el nombre del TipoImpresion */
    private String tipo;

    /**
     * Define el constructor de TDA TipoImpresion.
     * @param id Identificador del TipoImpresion.
     * @param tipo Nombre del TipoImpresion.
     */
    private TipoImpresion(int id, String tipo)
    {
        this.id = id;
        this.tipo = tipo;
    }

    /**
     * Obtiene el id del TipoImpresion.
     * @return Regresa el id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Obtiene el nombre del TipoImpresion.
     * @return Regresa el nombre.
     */
    public String getTipo()
    {
        return tipo;
    }
}
