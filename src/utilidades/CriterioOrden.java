package utilidades;

/**
 * Este enum implementa el TDA CriterioOrden.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum  CriterioOrden
{
    /** Valor para indicar un ordenamiento de forma ascendente. */
    ASCENDENTE("ASC",1),
    /** Valor para indicar un ordenamiento de forma descendente. */
    DESCENDENTE("DESC", 2);

    private String nombre;
    private int id;

    /**
     * Define el constructor de TDA CriterioOrden.
     * @param nombre Nombre que llevará el criterio de orden.
     * @param id Identificador para el criterio de orden.
     */
    private CriterioOrden(String nombre, int id)
    {
        this.nombre = nombre;
        this.id = id;
    }

    /**
     * Obtiene el nombre asignado al objeto.
     * @return Regresa el atributo nombre del objeto.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Obtiene el id asignado al objeto.
     * @return Regresa el atributo id del objeto.
     */
    public int getId()
    {
        return id;
    }
}
