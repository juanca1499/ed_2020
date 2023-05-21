package catalogos;

/**
 * Implementa el TDA Vértice. Los vértices
 * componen a un Grafo, por tanto, esta clase
 * almacena la información de cada uno de los
 * elementos de dicha estructura de datos.
 * @author Clase de Estructura de Datos.
 * @version 1.0.
 */
public class Vertice
{
    /** Almacena el contenido del nodo vértice. */
    protected Object nombre;
    /** Almacena el número de vértice usado para su mapeo con la matriz de aristas. */
    protected int numVertice;

    /**
     * Define el constructor TDA Vertice.
     * @param nombre Contenido a ser asignado.
     * @param numVertice Valor numérico del vertice respecto
     *                   a la matriz de aristas.
     */
    public Vertice(Object nombre, int numVertice)
    {
        this.nombre = nombre;
        this.numVertice = numVertice;
    }

    /**
     * Define el constructor TDA Vertice.
     * @param nombre Contenido a ser asignado.
     */
    public Vertice(Object nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Asigna un contenido al vértice.
     * @param nombre Contenido a ser asignado.
     */
    public void setNombre(Object nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Recupera el contenido del vértice.
     * @return Regresa el campo nombre.
     */
    public Object getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un número al vértice.
     * @param numVertice Valor a ser asignado.
     */
    public void setNumVertice(int numVertice)
    {
        this.numVertice = numVertice;
    }

    /**
     * Recupera el número del vértice.
     * @return Regresa el campo numVertice.
     */
    public int getNumVertice()
    {
        return numVertice;
    }

    @Override
    public String toString()
    {
        return nombre.toString();
    }

    /**
     * Obtiene la información de todos los campos
     * del vértice.
     * @return Regresa una cadena de texto
     * con el nombre y número de vértice asignado.
     */
    public String obtenerDatos()
    {
       return nombre + "(" + numVertice + ")";
    }


}
