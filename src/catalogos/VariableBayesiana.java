package catalogos;

import ednolineal.ArbolProbabilidad;

/**
 * Esta clase implementa el TDA VariableBayesiana.
 * Contiene la información para cada uno de los nodos
 * de un TDA RedBayesiana.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class VariableBayesiana
{
    /** Guarda el identificador de la variable. */
    private Object clave;
    /** Guarda el nombre completo de la variable. */
    private Object nombre;
    /** Guarda todos los casos probabilísticos de la variable. */
    private ArbolProbabilidad arbolProbabilidad;

    /**
     * Define el constructor TDA VariableBayesiana.
     */
    public VariableBayesiana()
    {
       this(null, null);
    }

    /**
     * Define el constructor TDA VariableBayesiana.
     * @param clave Identificador corto de la variable.
     * @param nombre Nombre completo de la variable.
     */
    public VariableBayesiana(Object clave, Object nombre)
    {
        arbolProbabilidad = new ArbolProbabilidad();
        this.clave = clave;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador corto de la variable.
     * @return Regresa el campo clave.
     */
    public Object getClave()
    {
        return clave;
    }

    /**
     * Asigna un identificador corto a la variable.
     * @param clave Valor a ser asignado.
     */
    public void setClave(Object clave)
    {
        this.clave = clave;
    }

    /**
     * Obtiene el nombre completo de la variable.
     * @return Regresa el campo nombre.
     */
    public Object getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un nombre extenso a la variable.
     * @param nombre Valor a ser asignado.
     */
    public void setNombre(Object nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Obtiene el arbol de combinaciones de probabilidad
     * de la variable.
     * @return Regresa el campo arbolProbabilidad.
     */
    public ArbolProbabilidad getArbolProbabilidad()
    {
        return arbolProbabilidad;
    }

    @Override
    public String toString()
    {
        return clave.toString();
    }
}
