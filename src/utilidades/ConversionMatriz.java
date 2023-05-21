package utilidades;

/**
 * Este enum implementa el TDA ConversionMatriz.
 * Contiene los tipos de extracción de datos de
 * una Matriz.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum ConversionMatriz
{

    /** Define una conversión en base a los renglones de la Matriz. */
    POR_RENGLON(1, "Renglon"),
    /** Define una conversión en base a las columnas de la Matriz. */
    POR_COLUMNA(2,"Columna"),
    /** Define una conversión en base a la profundidad de la Matriz. */
    POR_PROFUNDIDAD(3, "Profundidad");


    /** Guarda el identificador del tipo de ConversionMatriz. */
    private int id;
    /** Guarda el nombre del tipo de ConversionMatriz. */
    private String nombre;

    /**
     * Define el constructor de TDA ConversionMatriz.
     * @param id Identificador para el tipo de ConversionMatriz.
     * @param nombre Nombre que llevará el tipo de ConversionMatriz.
     */
    private ConversionMatriz(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id asignado al tipo ConversionMatriz.
     * @return Regresa el atributo id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Obtiene el nombre asignado al tipo ConversionMatriz.
     * @return Regresa el atributo nombre.
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
