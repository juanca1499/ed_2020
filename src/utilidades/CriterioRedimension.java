package utilidades;

/**
 * Este enum define el TDA CriterioRedimension.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum CriterioRedimension
{

    /** Valor para indicar un aumento en la dimensión. */
    AUMENTAR(1,"Aumentar"),
    /** Valor para indicar una reducción en la dimensión. */
    REDUCIR(2, "Reducir");


    /** Identificador que recibirá el criterio de redimensión. */
    private int id;
    /** Nombre que recibirá criterio de redimensión. */
    private String nombre;

    /**
     * Define el constructor de TDA CriterioRedimensión.
     * @param id Identificador para el criterio de redimensión.
     * @param nombre Nombre que llevará el criterio de redimensión.
     */
    private CriterioRedimension(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id asignado al criterio de redimensión.
     * @return Regresa el atributo id del criterio de redimensión.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Obtiene el nombre asignado al criterio de redimensión.
     * @return Regresa el atributo nombre del criterio de redimensión.
     */
    public String getNombre()
    {
        return nombre;
    }
}
