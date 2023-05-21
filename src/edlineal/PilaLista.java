package edlineal;

/**
 * Esta clase define el TDA PilaLista.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PilaLista implements IPila
{
    /** Guarda los elementos contenidos en la PilaLista. */
    private ListaLigada datosPila;

    /**
     * Define el constructor TDA PilaLista.
     */
    public PilaLista()
    {
        datosPila = new ListaLigada();
    }

    /**
     * Indica si la PilaLista está vacía.
     * @return Regresa <b>true</b> si la PilaLista está vacía, <b> false </b>
     * en caso contrario.
     */
    @Override
    public boolean vacia()
    {
        return datosPila.vacia();
    }

    /**
     * Indica si la PilaLista está llena.
     * @return Regresa <b>true</b> si la PilaLista está llena, <b> false </b>
     * en caso contrario.
     */
    @Override
    public boolean llena()
    {
        return datosPila.llena();
    }

    /**
     * Agrega a la PilaLista el objeto pasado como parámetro,
     * esté valor es agregado después del último elemento
     * insertado.
     * @param valor Elemento a ser agregado a la PilaLista.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el objeto, <b>false</b> en caso contrario.
     */
    @Override
    public boolean push(Object valor)
    {
        return datosPila.agregar(valor);
    }

    /**
     * Elimina el último elemento insertado en la PilaLista.
     * @return Regresa el objeto que fue eliminado en
     * caso de haber podido, o null en caso contrario.
     */
    @Override
    public Object pop()
    {
        return datosPila.eliminarTope();
    }

    /**
     * Imprime en la salida estándar los elementos
     * contenidos en la PilaLista, desde el último
     * que fue insertado, hasta el primero.
     */
    @Override
    public void imprimir()
    {
        datosPila.imprimirR();
    }

    /**
     * Recupera el valor que está al tope de la PilaLista,
     * (último insertado).
     * @return Regresa último Objeto insertado.
     */
    @Override
    public Object verTope()
    {
        return datosPila.getTope();
    }
}
