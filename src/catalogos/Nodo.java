package catalogos;

/**
 * Esta clase implementa el TDA Nodo.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Nodo
{
    /** Guarda el objeto con la información que tendrá el Nodo. */
    protected Object info;
    /** Guarda la dirección de memoria donde está el siguiente Nodo. */
    protected Nodo ligaDerecha;

    /**
     * Define el constructor de TDA Nodo.
     * @param valor Es la información que tendrá el Nodo.
     */
    public Nodo(Object valor)
    {
        this.info = valor;
        ligaDerecha = null;
    }

    /**
     * Asigna al Nodo el objeto con la información
     * que contendrá en su interior.
     * @param valorNuevo Información a guardar en el Nodo.
     */
    public void setInfo(Object valorNuevo)
    {
        info = valorNuevo;
    }


    public void setLigaDerecha(Nodo liga)
    {
        this.ligaDerecha = liga;
    }

    public Object getInfo()
    {
        return info;
    }

    public Nodo getLigaDerecha()
    {
        return ligaDerecha;
    }

    @Override
    public String toString()
    {
        return info.toString();
    }
}
