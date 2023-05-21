package utilidades;

/**
 * Este enum define el TDA Sexo.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public enum Sexo
{

    /** Valor para indicar el sexo masculino. */
    MASCULINO(1, "Masculino"),
    /** Valor para indicar el sexo femenino. */
    FEMENINO(2, "Femenino");

    /** Identificador que recibirá el tipo de sexo. */
    private int id;
    /** Nombre que recibirá el tipo de sexo. */
    private String nombre;


    /**
     * Define el constructor de TDA Sexo.
     * @param id Identificador para el sexo.
     * @param nombre Nombre que llevará el sexo.
     */
    private Sexo(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id asignado al sexo.
     * @return Regresa el atributo id del sexo.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Obtiene el nombre asignado al sexo.
     * @return Regresa el atributo nombre del sexo.
     */
    public String getNombre()
    {
        return nombre;
    }
}
