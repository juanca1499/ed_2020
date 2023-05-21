package matematicas;

/**
 * Este enum implementa el TDA TipoLogaritmo.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum TipoLogaritmo
{
    /** Valor para indicar un logaritmo de base 10. */
    BASE10("B10", 1),
    /** Valor para indicar un logaritmo natural. */
    NATURAL("NAT", 2);

    /** Nombre que recibirá el tipo de logaritmo. */
    private String nombre;
    /** Identificador para el tipo de logaritmo. */
    private int id;

    /**
     * Define el constructor de TDA TipoLogaritmo.
     * @param nombre Nombre que llevará el tipo de logaritmo.
     * @param id Identificador para el tipo de logaritmo.
     */
    private TipoLogaritmo(String nombre, int id)
    {
        this.nombre = nombre;
        this.id = id;
    }

    /**
     * Obtiene el nombre asignado al tipo de logaritmo.
     * @return Regresa el atributo <b>nombre</b>.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Obtiene el id asignado al tipo de logaritmo.
     * Regresa el atributo <b>id</b>.
     * @return Regresa el atributo <b>id</b>.
     */
    public int getId()
    {
        return id;
    }
}
