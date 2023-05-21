package ednolineal;

import catalogos.Nodo;
import catalogos.NodoDoble;
import edlineal.ListaLigada;
import entradasalida.SalidaEstandar;
import utilidades.Comparador;

/**
 * Esta clase implementa el TDA ArbolProbabilidad.
 * Contiene todas las combinaciones probabilísticas
 * para una variable o nodo de una RedBayesiana. La cantidad de
 * combinaciones depende de la cantidad de padres
 * que tiene el nodo en cuestión.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class ArbolProbabilidad extends ArbolBinario
{
    /**
     * Define el constructor TDA ArbolProbabilidad.
     * La raiz de este arbol por defecto siempre es cero y
     * sus hojas de comienzo -1 y 1.
     */
    public ArbolProbabilidad()
    {
        raiz = new NodoDoble(0);
        raiz.setLigaIzquierda(new NodoDoble(-1));
        raiz.setLigaDerecha(new NodoDoble(1));
    }

    /**
     * Asigna la probabilidad a uno de los casos
     * de una variable.
     * @param coordenadas Lugar en el arbol donde será
     *                    guardado la probabilidad. Las
     *                    coordenadas indican en
     *                    sus valores el caso o combinación
     *                    que se está tomando en cuenta.
     * @param valor Probabilidad a ser asignada.
     * @return Regresa <b>true</b> si se pudo asignar la
     * probabilidad, o <b>false</b> en caso contrario.
     */
    public boolean asignar(ListaLigada coordenadas, double valor)
    {
        coordenadas.inicializaIterador();
        return asignar(coordenadas, valor, raiz);
    }

    /**
     * Recorre recursivamente el arbol para asignar la
     * probabilidad a uno de los casos de una variable.
     * @param coordenadas Lugar en el arbol donde será
     *                    guardada la probabilidad. Las
     *                    coordenadas indican en
     *                    sus valores el caso o combinación
     *                    que se está tomando en cuenta.
     * @param valor Probabilidad a ser asignada.
     * @param subRaiz Nodo actual del recorrido.
     * @return Regresa <b>true</b> si se pudo asignar la
     * probabilidad, o <b>false</b> en caso contrario
     */
    private boolean asignar(ListaLigada coordenadas, double valor,
                            NodoDoble subRaiz)
    {
        if(subRaiz != null)
        {
            if(!coordenadas.hayNodoSiguiente())
            {// Ya fueron tomadas todas las coordenadas, se tiene que asignar
             // la probabilidad
                subRaiz.setInfo(valor);
                return true;
            }
            else
            {// Se continúa avanzando sobre el árbol hasta la posición destino.

                // Se obtiene el lado al que se avanzará en el árbol:
                // -1 a la izquierda, 1 a la derecha.
                int direccion = (int) coordenadas.obtenerSiguiente();

                if(direccion == -1)
                {// Se avanza hacia la rama izquierda.
                    return asignar(coordenadas, valor, subRaiz.getLigaIzquierda());
                }
                else
                {// Se avanza hacia la rama derecha.
                    return asignar(coordenadas, valor, subRaiz.getLigaDerecha());
                }
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Extrae el valor de uno de los casos de
     * probabilidad.
     * @param coordenadas Lugar en el arbol donde se
     *                    encuentra el valor de probabilidad.
     *                    Las coordenadas indican en
     *                    sus valores el caso o combinación
     *                    que se está tomando en cuenta.
     * @return Regresa el valor de probabilidad si fue encontrado,
     * o <b>Double.NaN</b> en caso contrario.
     */
    public double buscar(ListaLigada coordenadas)
    {
        coordenadas.inicializaIterador();
        return buscar(coordenadas, raiz);
    }

    /**
     * Recorre recursivamente el arbol para
     * extraer el valor de uno de los casos de
     * probabilidad.
     * @param coordenadas Lugar en el arbol donde se
     *                    encuentra el valor de probabilidad.
     *                    Las coordenadas indican en
     *                    sus valores el caso o combinación
     *                    que se está tomando en cuenta.
     * @param subRaiz Nodo actual del recorrido.
     * @return Regresa el valor de probabilidad si fue encontrado,
     * o <b>Double.NaN</b> en caso contrario.
     */
    private double buscar(ListaLigada coordenadas, NodoDoble subRaiz)
    {
        if(!coordenadas.hayNodoSiguiente() && subRaiz != null)
        {// Caso base, sí fue encontrado el valor.
            return (double)subRaiz.getInfo();
        }
        else if(!coordenadas.hayNodoSiguiente() && subRaiz == null)
        {// Caso base, no fue encontrado el valor.
            return Double.NaN;
        }
        else
        {// Se continúa con el recorrido.

            // Se obtiene el lado al que se avanzará en el árbol:
            // -1 a la izquierda, 1 a la derecha.
            int direccion = (int) coordenadas.obtenerSiguiente();

            if(direccion == -1)
            {// Se avanza hacia la rama izquierda.
                return buscar(coordenadas, subRaiz.getLigaIzquierda());
            }
            else
            {// Se avanza hacia la rama derecha.
                return buscar(coordenadas, subRaiz.getLigaDerecha());
            }
        }
    }

    /**
     * Incrementa la cantidad de combinaciones de
     * probabilidades que puede tener el árbol.
     */
    public void aumentarCasos()
    {
        aumentarCasos(raiz);
    }

    /**
     * Recorre el arbol recursivamente y agrega más nodos
     * para tener espacio para cubrir todas las combinaciones
     * de probabilidades.
     * @param subRaiz Nodo actual del recorrido.
     * @param caso Tipo de caso actual:
     *             -1 negación,
     *             1 afirmación.
     */
    private void aumentarCasos(NodoDoble subRaiz)
    {
        if(subRaiz != null)
        {// La subraíz es válida.

            if(esHoja(subRaiz))
            {// Se aumentan los casos a partir de las
             // hojas actuales.

                // Se asignan negaciones hacia la izquierda.
                subRaiz.setLigaIzquierda(new NodoDoble(-1));
                // Se asignan afirmaciones hacia la derecha.
                subRaiz.setLigaDerecha(new NodoDoble(1));
            }
            else
            {// Se continúa con el recorrido.
                aumentarCasos(subRaiz.getLigaIzquierda());
                aumentarCasos(subRaiz.getLigaDerecha());
            }
        }
    }
}
