package catalogos;

/**
 * Esta clase implementa el TDA NodoHash.
 * Almacena datos a manera de tabla hash (clave, valor).
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class NodoHash
{
    /** Guarda la clave que identifica al valor del NodoHash. */
    private Object clave;
    /** Guarda el objeto con la información que tendrá el NodoHash. */
    private Object valor;
    /** Guarda la dirección de memoria donde está el siguiente NodoHash. */
    private NodoHash ligaDerecha;

    /**
     * Define el constructor de TDA NodoHash.
     * @param clave Identificador que tendrá el valor del NodoHash.
     * @param valor Es la información o valor que tendrá el NodoHash.
     */
    public NodoHash(Object clave, Object valor)
    {
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * Asigna la clave que identificará a la información del NodoHash.
     * @param clave Identificador del valor.
     */
    public void setClave(Object clave)
    {
        this.clave = clave;
    }

    /**
     * Recupera la clave que identifica al información del NodoHash.
     * @return Regresa la clave del valor del NodoHash.
     */
    public Object getClave()
    {
        return clave;
    }

    /**
     * Asigna el valor o información que contendrá el NodoHash.
     * @param valor Información a guardarse.
     */
    public void setValor(Object valor)
    {
        this.valor = valor;
    }

    /**
     * Recupera el valor o información que está contenida en el NodoHash.
     * @return Regresa el objeto con la información.
     */
    public Object getValor()
    {
        return valor;
    }

    /**
     * Asigna la dirección de memoria donde se encuentra el siguiente
     * NodoHash respecto al actual.
     * @param ligaDerecha NodoHash siguiente del actual.
     */
    public void setLigaDerecha(NodoHash ligaDerecha)
    {
        this.ligaDerecha = ligaDerecha;
    }

    /**
     * Recupera el NodoHash que está después del actual.
     * @return Regresa el siguiente NodoHash.
     */
    public NodoHash getLigaDerecha()
    {
        return ligaDerecha;
    }

    @Override
    public String toString()
    {
        return clave + "(" + valor + ")";
    }
}
