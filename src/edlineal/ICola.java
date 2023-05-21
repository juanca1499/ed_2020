package edlineal;

/**
 * Define los métodos o el comportamiento
 * que la estructura de datos "cola" tiene.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo
 * @version 1.0
 */
public interface ICola
{
    /**
     * Indica si la ICola se encuentra vacía.
     * @return Regresa <b>true</b> en caso de estar vacía,
     * o <b>false</b> en caso contrario.
     */
    boolean vacia();

    /**
     * Determina si la ICola está llena.
     * @return Regresa <b>true</b> si está llena, <b>false</b>
     * en caso contrario.
     */
    boolean llena();

    /**
     * Permite agregar un valor a la ICola.
     * @param valor Elemento a ser insertado.
     * @return Regresa <b>true</b> en caso de poder insertarlo,
     * o <b>false</b> en caso contrario.
     */
    boolean agregar(Object valor);

    /**
     * Remueve el primer elemento que fue insertado en la
     * ICola.
     * @return Regresa el elemento eliminado.
     */
    Object eliminar();

    /**
     * Imprime en la salida estándar los valores
     * contenidos en la ICola en el órden en
     * que fueron insertados.
     */
    void imprimir();
}
