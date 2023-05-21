package catalogos;


import java.util.Objects;

/**
 * Esta clase implementa el TDA NodoDoble, que almacena
 * la liga derecha, la liga izquierda y el objeto con su información.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class NodoDoble
{
    /** Guarda el objeto con la información que tendrá el NodoDoble. */
    protected Object info;
    /** Guarda la dirección de memoria donde está el NodoDoble del lado izquierdo. */
    protected NodoDoble ligaIzquierda;
    /** Guarda la dirección de memoria donde está el NodoDoble del lado derecho. */
    protected NodoDoble ligaDerecha;

    /**
     * Define el constructor TDA NodoDoble.
     */
    public NodoDoble()
    {
        info = null;
        ligaIzquierda = null;
        ligaDerecha = null;
    }

    /**
     * Define el constructor de TDA NodoDoble.
     * @param valor Es la información que contendrá el NodoDoble.
     */
    public NodoDoble(Object valor)
    {
        info = valor;
        ligaIzquierda = null;
        ligaDerecha = null;
    }

    public void setInfo(Object valor)
    {
        info = valor;
    }

    public void setLigaIzquierda(NodoDoble ligaIzquierda)
    {
        this.ligaIzquierda = ligaIzquierda;
    }

    public void setLigaDerecha(NodoDoble ligaDerecha)
    {
        this.ligaDerecha = ligaDerecha;
    }

    public Object getInfo()
    {
        return info;
    }

    public NodoDoble getLigaIzquierda()
    {
        return ligaIzquierda;
    }

    public NodoDoble getLigaDerecha()
    {
        return ligaDerecha;
    }

    /**
     * Realiza una copia del mismo nodo.
     * Solamente copia el atributo info mas no las ligas
     * izquierda y derecha.
     * @return Regresa una instancia de NodoDoble.
     */
    public NodoDoble clonar()
    {
        NodoDoble nodoDobleCopia = new NodoDoble(info);
        return nodoDobleCopia;
    }

    /**
     * Añade un nuevo nodo a la primer liga que
     * se encuentre nula, primero comprueba con la
     * izquierda y después con la derecha.
     * @param nodo Nuevo nodo que será agregado.
     * @return Regresa <b>true</b> en caso de haber
     * podido agregar la liga, o <b>false</b> en caso
     * contrario.
     */
    public boolean agregarLiga(NodoDoble nodo)
    {
        // El nodo es válido.
        if(nodo != null)
        {
            // La liga izquierda está vacía.
            if(ligaIzquierda == null)
            {
                ligaIzquierda = nodo;
                return true;
            }
            // La liga derecha está vacia.
            else if(ligaDerecha == null)
            {
                ligaDerecha = nodo;
                return true;
            }
            else
            {
                return false;
            }
        }
        // El nodo es inválido.
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return info.toString();
    }
}
