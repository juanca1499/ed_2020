package catalogos;

/**
 * Esta clase implementa el TDA Proceso.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Proceso
{

    /** Guarda el nombre o descripción que posee el Proceso. */
    private String nombre;
    /** Guarda el nivel de prioridad que posee el Proceso. */
    private int prioridad;

    /**
     * Define el constructor de TDA Proceso.
     * @param nombre Descripción o nombre que recibe el Proceso.
     * @param prioridad Nivel de prioridad que tiene el Proceso.
     */
    public Proceso(String nombre, int prioridad)
    {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    /**
     * Obtiene el nombre o descripción que fue asignado
     * al Proceso.
     * @return Regresa el nombre o descripción del Proceso.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene el nivel de prioridad que fue asignado
     * al Proceso.
     * @return Regresa la prioridad del Proceso.
     */
    public int getPrioridad() { return prioridad; }

    @Override
    public String toString()
    {
        return nombre + "(" + prioridad + ")";
    }
}
