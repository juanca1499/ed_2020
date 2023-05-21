package catalogos;


/**
 * Esta clase implementa el TDA NodoDobleIndexado, que almacena el index,
 * la liga derecha, la liga izquierda y el objeto con su información.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class NodoDobleIndexado
{

    /** Guarda el index o identificador del Nodo. */
    private int index;
    /** Guarda el objeto con la información que tendrá el Nodo. */
    protected Object info;
    /** Guarda la dirección de memoria donde está el Nodo del lado izquierdo. */
    private NodoDobleIndexado ligaIzquierda;
    /** Guarda la dirección de memoria donde está el Nodo del lado derecho. */
    private NodoDobleIndexado ligaDerecha;


    /**
     * Define el constructor de TDA NodoDobleIndexado.
     * @param index Índice del Nodo.
     * @param valor Es la información que contendrá el NodoDobleIndexado.
     */
    public NodoDobleIndexado(int index, Object valor)
    {
        this.index = index;
        this.info = valor;
        ligaDerecha = null;
        ligaIzquierda = null;
    }

    /**
     * Obtiene el identificador del Nodo.
     * @return Regresa el identificador.
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Asigna el identificador al Nodo.
     * @param index Valor que identificará al nodo.
     */
    public void setIndex(int index)
    {
        this.index = index;
    }

    /**
     * Recupera el valor o información que está contenida en el Nodo.
     * @return Regresa el objeto con la información.
     */
    public Object getInfo()
    {
        return info;
    }

    /**
     * Asigna el valor o información que contendrá el Nodo.
     * @param info Información a guardarse.
     */
    public void setInfo(Object info)
    {
        this.info = info;
    }

    /**
     * Recupera el NodoDobleIndexado que está antes del actual.
     * @return Regresa el anterior NodoDobleIndexado.
     */
    public NodoDobleIndexado getLigaIzquierda()
    {
        return ligaIzquierda;
    }

    /**
     * Asigna la dirección de memoria donde se encuentra el anterior
     * Nodo respecto al actual.
     * @param ligaIzquierda NodoDobleIndexado anterior del actual.
     */
    public void setLigaIzquierda(NodoDobleIndexado ligaIzquierda)
    {
        this.ligaIzquierda = ligaIzquierda;
    }

    /**
     * Recupera el NodoDobleIndexado que está después del actual.
     * @return Regresa el siguiente NodoDobleIndexado.
     */
    public NodoDobleIndexado getLigaDerecha()
    {
        return ligaDerecha;
    }

    /**
     * Asigna la dirección de memoria donde se encuentra el siguiente
     * Nodo respecto al actual.
     * @param ligaDerecha NodoDobleIndexado siguiente del actual.
     */
    public void setLigaDerecha(NodoDobleIndexado ligaDerecha)
    {
        this.ligaDerecha = ligaDerecha;
    }

    @Override
    public String toString()
    {
        return index + "(" + info + ")";
    }
}
