package edlineal;

/**
 * Esta clase define el TDA Pila.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Pila implements IPila
{
    /** Guarda el contenido de los elementos de la Pila.*/
    private Arreglo datosPila;

    /**
     * Define el constructor de TDA Pila.
     * @param tam Es el tamaño para crear la Pila.
     */
    public Pila(int tam)
    {
        datosPila = new Arreglo(tam);
    }

    /**
     * Indica si la Pila está vacía.
     * @return Regresa <b>true</b> si la Pila está vacía, <b> false </b>
     * en caso contrario.
     */
    @Override
    public boolean vacia()
    {
        return datosPila.vacia();
    }

    /**
     * Indica si la Pila está llena.
     * @return Regresa <b>true</b> si la Pila está llena, <b> false </b>
     * en caso contrario.
     */
    @Override
    public boolean llena()
    {
        return datosPila.llena();
    }

    /**
     * Agrega a la Pila el objeto pasado como parámetro,
     * esté elemento es agregado en la última posicición
     * disponible.
     * @param valor Elemento a ser agregado a la pila.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el objeto, <b>false</b> en caso contrario.
     */
    @Override
    public boolean push(Object valor)
    {
        return datosPila.agregar(valor);
    }

    /**
     * Elimina el último elemento insertado en la Pila.
     * @return Regresa el objeto que fue eliminado en
     * caso de haber podido, o null en caso contrario.
     */
    @Override
    public Object pop()
    {
        return datosPila.eliminarTope();
    }

    @Override
    public void imprimir()
    {
        datosPila.imprimirR();
    }

    @Override
    public Object verTope()
    {
        return datosPila.getTope();
    }
}
