package utilidades;

/**
 * Esta clase implementa el TDA TipoFuncionNeurona.
 * Define las posibles funciones que puede tomar
 * una neurona en una red neuronal.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public enum TipoFuncionActivacion
{
    /** Define un tipo de función lineal. */
    LINEAL(1, "Lineal"),
    /** Define un tipo de función sigmoideal. */
    SIGMOIDEAL(2, "Sigmoideal"),
    /** Define un tipo de función SoftMax. */
    SOFTMAX(3, "SoftMax");

    /** Guarda el identificador de la función. */
    private int id;
    /** Guarda el nombre de la función. */
    private String nombre;

    /**
     * Define el constructor TDA TipoFuncionNeurona.
     * @param id Identificador de la función.
     * @param nombre Nombre de la función.
     */
    private TipoFuncionActivacion(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Rcupera el identificador de la función.
     * @return Regresa el campo id.
     */
    public int getId() {
        return id;
    }

    /**
     * Recupera el nombre de la función.
     * @return Regresa el campo nombre.
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
