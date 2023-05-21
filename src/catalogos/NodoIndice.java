package catalogos;

import org.omg.PortableInterceptor.LOCATION_FORWARD;

/**
 * Contiene los campos necesarios para guardar
 * una indexación de un archivo de datos.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class NodoIndice
{
    /** Guarda la clave principal o el identificador del dato. */
    private Integer indice;
    /** Guarda la dirección de memoria donde se encuentra el dato. */
    private Long direccion;

    /**
     * Define el constructor TDA NodoIndice.
     */
    public NodoIndice()
    {
        indice = null;
        direccion = null;
    }

    /**
     * Define el constructor TDA NodoIndice.
     * @param indice Identificador del dato.
     * @param direccion Dirección de memoria del dato.
     */
    public NodoIndice(Integer indice, Long direccion)
    {
        this.indice = indice;
        this.direccion = direccion;
    }

    /**
     * Asigna el identificador al Nodo.
     * @param indice Valor a ser asignado al campo
     *               indice.
     */
    public void setIndice(Integer indice)
    {
        this.indice = indice;
    }

    /**
     * Recupera el indice del nodo.
     * @return Regresa el campo indice.
     */
    public Integer getIndice()
    {
        return indice;
    }

    /**
     * Asigna la dirección al Nodo.
     * @param direccion Valor a ser asignado en el
     *                  campo direccion.
     */
    public void setDireccion(Long direccion)
    {
        this.direccion = direccion;
    }

    /**
     * Recupera la direccion del nodo.
     * @return Regresa el campo direccion.
     */
    public Long getDireccion()
    {
        return direccion;
    }


    @Override
    public String toString()
    {
        return "" + indice;
    }
}
