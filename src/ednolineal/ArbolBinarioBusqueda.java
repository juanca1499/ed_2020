package ednolineal;

import catalogos.NodoDoble;
import entradasalida.SalidaEstandar;
import utilidades.Comparador;
import utilidades.ConversionMatriz;

/**
 * Esta clase implementa el TDA ArbolBinarioBusqueda.
 * El arbol binario de busqueda almacena nodos que en
 * su contenido guardan valores númericos. La estructura
 * del árbol consiste en que de forma recursiva los nodos
 * a la izquierda de la raíz o de una subraíz su valor numérico
 * es más pequeño, en cambio, los del lado derecho contienen
 * un valor más grande. Cabe mencionar también que no se permiten
 * valores repetidos en el árbol.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ArbolBinarioBusqueda extends ArbolBinario
{
    /**
     * Define el constructor TDA ArbolBinarioBusqueda.
     */
    public ArbolBinarioBusqueda()
    {
        super();
    }

    /**
     * Método que inserta un elemento en un Arbol BB.
     * @param valor Es el nuevo elemento a insertar.
     * @return Regresa <b>true</b> si pudo insertarlo, o
     * <b>false</b> en caso contrario.
     */
    public boolean insertar(Object valor)
    {
        // Si la raíz es nula, es el primer nodo.
         if(raiz == null)
         {
             NodoDoble nodoNuevo = new NodoDoble(valor);
            // Si no hay error y se puede asignar memoria.
             if(nodoNuevo != null)
             {
                    raiz = nodoNuevo;
                    return true;
             }
             // Error en la dirección de memoria.
             else
             {
                 return false;
             }
         }
         // No es nula la raíz, quiere decir que hay algo.
         else
         {
             return insertar(raiz, valor);
         }
    }

    /**
     * Busca recursivamente el lugar donde se debe insertar
     * el nuevo nodo junto con su valor.
     * @param subRaiz Nodo analizado en curso.
     * @param valor Elemento a ser insertado.
     * @return Regresa <b>true</b> en caso de haber podido
     * insertar el nuevo nodo junto con su valor, o <b>false</b>
     * en caso contrario.
     */
    private boolean insertar(NodoDoble subRaiz, Object valor)
    {
        // Si el valor del nuevo nodo es menor a la subraiz, vamos a
        // la izquierda.
        if(Comparador.comparar(valor, subRaiz.getInfo()) < 0)
        {
            if(subRaiz.getLigaIzquierda() == null)
            {
                // Insertarlo.
                NodoDoble nodoNuevo = new NodoDoble(valor);
                // Sí se pudo crear el espacio de memoria.
                if(nodoNuevo != null)
                {
                    subRaiz.setLigaIzquierda(nodoNuevo);
                    return true;
                }
                else
                {
                    // Eror en asignar memoria.
                    return false;
                }
            }
            // Me voy a su liga izquierda.
            else
            {
                return insertar(subRaiz.getLigaIzquierda(), valor);
            }
        }
        else if(Comparador.comparar(valor, subRaiz.getInfo()) > 0)
        {
            // Si el valor nuevo a insertar es mayor a la subraíz vamos
            // a la derecha.
            if(subRaiz.getLigaDerecha() == null)
            {
                NodoDoble nodoNuevo = new NodoDoble(valor);
                // Insertarlo.
                if(nodoNuevo != null)
                {
                    subRaiz.setLigaDerecha(nodoNuevo);
                    return true;
                }
                // Error en asignar memoria.
                else
                {
                    return false;
                }
            }
            // Me voy a su liga derecha.
            else
            {
                return insertar(subRaiz.getLigaDerecha(), valor);
            }
        }
        // Es igual, entones no se inserta.
        else
        {
            return false;
        }
    }

    /**
     * Verifica si existe un elemento en el Árbol.
     * @param valor Elemento a ser buscado.
     * @return En caso de existir el elemento, éste
     * es regresado, si no se encuentra se regresa
     * null.
     */
    public Object buscar(Object valor)
    {
        return buscar(raiz, valor);
    }

    /**
     * Recorre recursivamente el Árbol en busca de
     * la existencia de un valor.
     * @param subRaiz Nodo analizado en curso.
     * @param valor Elemento a ser buscado.
     * @return En caso de existir el elemento, éste
     * es regresado, si no se encuentra se regresa
     * null.
     */
    private Object buscar(NodoDoble subRaiz, Object valor)
    {
        // Caso base, ya no hay hijos en esa subraiz.
        if(subRaiz == null)
        {
            return null;
        }
        // Es una subraíz válida.
        else
        {
            // Son iguales.
            if(Comparador.comparar(valor, subRaiz.getInfo()) == 0)
            {
                return subRaiz.getInfo();
            }
            // El objeto buscado es menor a la subraíz.
            else if(Comparador.comparar(valor, subRaiz.getInfo()) < 0)
            {
                return buscar(subRaiz.getLigaIzquierda(), valor);
            }
            // El objeto buscado es mayor a la subraíz.
            else
            {
                return buscar(subRaiz.getLigaDerecha(), valor);
            }
        }
    }

    /**
     * Suprime un nodo del Árbol.
     * @param valor Elemento a ser eleminado.
     * @return Regresa el TDA del elemento borrado.
     */
    public Object eliminar(Object valor)
    {
        return eliminar(raiz,null, valor);
    }

    /**
     * Recorre de forma recursiva el Árbol en busca
     * del nodo que contenga el elemento a eliminar.
     * @param actual NodoDoble actual de la iteración.
     * @param anterior NodoDoble anterior al actual en
     *                 cada iteración.
     * @param info Valor a ser buscado entre los nodos
     *             para eliminarse.
     * @return Regresa el TDA del elemento borrado.
     */
    private Object eliminar(NodoDoble actual, NodoDoble anterior, Object info)
    {
        Object infoNodo = null;
        // Aún hay nodos para analizar.
        if(actual != null)
        {
            // El valor a elimianr es menor a la subraíz.
            if(Comparador.comparar(info, actual.getInfo()) < 0)
            {
                return eliminar(actual.getLigaIzquierda(), actual, info);
            }
            // El valor a eliminar es mayor a la subraíz.
            else if(Comparador.comparar(info, actual.getInfo()) > 0)
            {
                return eliminar(actual.getLigaDerecha(), actual, info);
            }
            // Se encontró el nodo a eliminar.
            else
            {
                infoNodo = actual.getInfo();
                // El nodo a eliminar tiene sus hijos derecho e izquierdo.
                if(actual.getLigaIzquierda() != null && actual.getLigaDerecha() != null)
                {
                    NodoDoble auxiliar = actual.getLigaIzquierda();
                    NodoDoble auxiliar1 = null;
                    boolean entroCiclo = false;

                    // Se busca el nodo más a la derecha del subárbol izquierdo.
                    while (auxiliar.getLigaDerecha() != null)
                    {
                        auxiliar1 = auxiliar;
                        auxiliar = auxiliar.getLigaDerecha();
                        entroCiclo = true;
                    }
                    actual.setInfo(auxiliar.getInfo());
                    // Se encontró el nodo más a la derecha.
                    if(entroCiclo == true)
                    {
                        auxiliar1.setLigaDerecha(auxiliar.getLigaIzquierda());
                    }
                    // El subárbol ya no tenía más hijos a la derecha.
                    else
                    {
                        actual.setLigaIzquierda(auxiliar.getLigaIzquierda());
                    }
                }
                // Alguno o ambos de los hijos del nodo a eliminar son nulos.
                else
                {
                    NodoDoble otro = actual;
                    // El hijo derecho del nodo a eliminar es nulo.
                    if(otro.getLigaDerecha() == null)
                    {
                        // El hijo izquierdo del nodo a eliminar no es nulo.
                        if(otro.getLigaIzquierda() != null)
                        {
                            otro = actual.getLigaIzquierda();
                            // El nodo anterior al que se va a eliminar no es nulo y se
                            // puede cambiar su hijo que será eliminado.
                            if(anterior != null)
                            {
                                // Se asigna el hijo izquierdo del nodo a eliminar
                                // a su padre en la liga izquierda.
                                if(Comparador.comparar(info, anterior.getInfo()) < 0)
                                {
                                    anterior.setLigaIzquierda(otro);
                                }
                                // Se asigna el hijo izquierdo del nodo a eliminar
                                // a su padre en la liga derecha.
                                else
                                {
                                    anterior.setLigaDerecha(otro);
                                }
                            }
                            // El nodo a eliminar era la raiz, se cambia esta
                            // por su hijo izquierdo.
                            else
                            {
                                raiz = otro;
                            }
                        }
                        // Ambos hijos del nodo son nulos.
                        else
                        {
                            // El nodo a eliminar era la raíz.
                            if(anterior == null)
                            {
                                raiz = null;
                            }
                            // El nodo a eliminar no es la raíz.
                            else
                            {
                                // El nodo a eliminar es el hijo izquierdo de anterior.
                                if(Comparador.comparar(info, anterior.getInfo()) < 0)
                                {
                                    anterior.setLigaIzquierda(null);
                                }
                                // El nodo a eliminar es el hijo derecho de anterior.
                                else
                                {
                                    anterior.setLigaDerecha(null);
                                }
                            }
                        }
                    }
                    // El hijo derecho no es nulo.
                    else
                    {
                        // Sólo el hijo izquierdo es nulo.
                        if(otro.getLigaIzquierda() == null)
                        {
                            otro = actual.getLigaDerecha();
                            // El nodo anterior no es nulo.
                            if(anterior != null)
                            {
                                // Se asigna el hijo derecho del nodo a eliminar
                                // a su padre en la liga izquierda.
                                if(Comparador.comparar(info, anterior.getInfo()) < 0)
                                {
                                    anterior.setLigaIzquierda(otro);
                                }
                                // Se asigna el hijo derecho del nodo a eliminar
                                // a su padre en la liga derecha.
                                else
                                {
                                    anterior.setLigaDerecha(otro);
                                }
                            }
                            // El nodo a eliminar era la raiz, se cambia esta
                            // por su hijo derecho.
                            else
                            {
                                raiz = otro;
                            }
                        }
                    }
                }
            }
            return infoNodo;
        }
        // Se recorrió el arbol y el nodo no fue encontrado.
        else
        {
            SalidaEstandar.consola("El dato no está en el árbol.");
            return null;
        }
    }

    @Override
    @Deprecated
    public void crearArbol()
    {
        SalidaEstandar.consola("Método Depreciado!");
    }

}
