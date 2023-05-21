/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edlineal;

/**
 *
 * @author Clase de estructura de datos
 * @version 1.0
 */

/**
 * Define la plantilla de un estructura de datos lineal
 * tipo Lista.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public interface Lista
{
    /**
     * Indica si la lista esta llena.
     * @return Regresa <b>true</b> si la lista está llena, <b> false </b>
     * en caso contrario.
     */
    public boolean llena();
    /**
     * Indica si la lista esta vacía.
     * @return Regresa <b>true</b> si la lista está vacía, <b> false </b>
     * en caso contrario.
     */
    public boolean vacia();
    /**
     * Inserta al final un elemento en la lista.
     * @param valor Es el elemento a insertar en la lista.
     * @return Regresa <b> true </b> si la lista insertó el elemento,
     * <b> false </b> en caso contrario.
     */
    public boolean agregar(Object valor);
    /**
     * Muestra los elementos de la lista en la salida estándar.
     */
    public void imprimirR();
    /**
     * Localiza la posición de valor.
     * @param valor Es el elemento a encontrar.
     * @return Regresa la posicion si lo encuentra, null en caso contrario.
     */
    public Object buscar(Object valor);
    /**
     * Suprime el elemento.
     * @param valor Es el elemento a borrar.
     * @return Regresa el objeto de la posicion encontrada o null si no
     * se encuentra.
     */
    public Object eliminar(Object valor);
    /**
     * Recorre la lista buscando elementos equivalentes a valorV,
     * cuando encuentra uno equivalente lo cambia por valorN. Este
     * proceso se repite la cantidad de veces indicada.
     * @param valorV Es el elemento a ser reemplazado.
     * @param valorN Es el elemento a asignar en el lugar de valorV.
     * @param numOcurrencias Cantidad de veces que se buscara hacer el remplazo.
     * @return Regresa <b>true</b> si realizó por lo menos una modificación,
     * <b>false</b> en caso contrario.
     */
    public boolean setElemento(Object valorV, Object valorN, int numOcurrencias);
    /**
     * Busca en la lista los elementos equivalentes a valor
     * y guarda las posiciones de estos en un objeto Arreglo.
     * @param valor Elemento a comparar para buscar ocurrencias.
     * @return Objeto de tipo Arreglo con las posiciones de
     * las ocurrencias encontradas.
     */
    public Arreglo buscarOcurrencias(Object valor);
    /**
     * Elimina todos los elementos de la lista.
     */
    public void vaciar();
    /**
     * Agrega al final del arreglo original la totalidad
     * o la mayor cantidad de elementos que se puedan del
     * segundo arreglo, en función de los espacios vacíos
     * en el arreglo original.
     * @param arreglo2 Elementos a agregarse al arreglo original.
     * @return Regresa <b>true</b> si por lo menos se agrego un
     * elemento al arreglo original, <b>false</b> en caso contrario.
     */
    public boolean agregarArreglo(Arreglo arreglo2);
    /**
     * Invierte la posición de los elementos contenidos en la lista.
     */
    public void invertir();
    /**
     * Enumera la cantidad de elementos en la lista que
     * son equivalentes a un valor dado.
     * @param valor Objeto a ser comparado con los elementos
     * de la lista.
     * @return Regresa la cantidad de coincidencias con
     *  el objeto encontradas.
     */
    public int contar(Object valor);
    /**
     * Remueve el ultimo elemento contenido en la lista.
     * @return Regresa el elemento que fue eliminado.
     */
    public Object eliminarTope();
    /**
     * Realiza la impresión de los elementos de la lista
     * partiendo de la primera posición a la última.
     */
    public void imprimir();
    /**
     * Agrega a la lista el valor dado la cantidad
     * de veces que se indique.
     * @param valor Elemento a insertarse.
     * @param cuantos Cantidad de veces que será insertado el elemento.
     */
    public void rellenar(Object valor, int cuantos);
    /**
     * Devuelve el contenido del último elemento en la Lista.
     * @return Regresa una copia del último elemento de la Lista
     * o nulo si no tiene nada.
     */
    public Object getTope();
}
