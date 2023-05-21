package utilidades;


/**
 * Este enum implementa el TDA ActividadPintor.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum ActividadPintor
{

    /** Valor para indicar una actividad de descanso. */
    DESCANSAR("Descansar", 1),
    /** Valor para indicar una actividad de inspiración. */
    INSPIRAR("Inspirar", 2),
    /** Valor para indicar una actividad de pintado. */
    PINTAR("Pintar", 3),
    /** Valor para indicar una actividad de exposición. */
    EXPONER("Exponer",4),
    /** Valor para indicar una actividad de viaje. */
    VIAJAR("Viajar",5);


    /** Nombre que recibirá el tipo de logaritmo. */
    private int id;
    /** Nombre que recibirá el tipo de logaritmo. */
    private String nombre;

    /**
     * Define el constructor de TDA ActividadPintor.
     * @param nombre Nombre que llevará la actividad.
     * @param id Identificador para la actividad.
     */
    private ActividadPintor(String nombre, int id)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id asignado a la actividad.
     * @return Regresa el atributo id de la actividad.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Obtiene el nombre asignado a la actividad.
     * @return Regresa el atributo nombre de la actividad.
     */
    public String getNombre()
    {
        return nombre;
    }
}
