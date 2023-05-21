package edlineal;

/**
 * Define los métodos o el comportamiento
 * que la estructura de datos "pila" tiene.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo
 * @version 1.0
 */
public interface IPila
{
    /**
     * Indica si la Pila está vacía.
     * @return Regresa <b>true</b> si la Pila está vacía, <b> false </b>
     * en caso contrario.
     */
    boolean vacia();

    /**
     * Indica si la Pila está llena.
     * @return Regresa <b>true</b> si la Pila está llena, <b> false </b>
     * en caso contrario.
     */
    boolean llena();

    /**
     * Agrega a la Pila el objeto pasado como parámetro,
     * esté elemento es agregado en la última posicición
     * disponible.
     * @param valor Elemento a ser agregado a la pila.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el objeto, <b>false</b> en caso contrario.
     */
    boolean push(Object valor);

    /**
     * Elimina el último elemento insertado en la Pila.
     * @return Regresa el objeto que fue eliminado en
     * caso de haber podido, o null en caso contrario.
     */
    Object pop();

    /**
     * Imprime en la salida estándar los elementos
     * contenidos en la Pila, desde el último
     * que fue insertado, hasta el primero.
     */
    void imprimir();

    /**
     * Recupera el valor que está al tope de la Pila,
     * (último insertado).
     * @return Regresa último Objeto insertado.
     */
    public Object verTope();
}
