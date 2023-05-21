package edlineal;

import catalogos.Proceso;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;


/**
 * Esta clase implementa el TDA ColaPrioridad.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ColaPrioridad
{
    /** Almacena los procesos a ser atendidos. */
    private Arreglo datos;
    /** Guarda la longitud de la ColaPrioridad. */
    private final int MAX;
    /** Guarda el tipo de prioridad que se dará a los procesos al ingresarse. */
    private CriterioOrden tipoPrioridad;

    /**
     * Define el constructor de TDA ColaPrioridad.
     * @param tamano Dimensión que tendrá la cola.
     * @param tipoPrioridad Criterio de prioridad que tendrán
     *                      los procesos al ser insetados en la
     *                      ColaPrioridad.
     */
    public ColaPrioridad(int tamano, CriterioOrden tipoPrioridad)
    {
       this.datos = new Arreglo(tamano);
       this.tipoPrioridad = tipoPrioridad;
       MAX = tamano;
    }

    /**
     * Indica si la ColaPrioridad está llena.
     * @return Regresa <b>true</b> si la ColaPrioridad está llena, <b> false </b>
     * en caso contrario.
     */
    public boolean estaLlena()
    {
        return datos.llena();
    }

    /**
     * Indica si la ColaPrioridad esta vacía.
     * @return Regresa <b>true</b> si la ColaPrioridad está vacía, <b> false </b>
     * en caso contrario.
     */
    public boolean estaVacia()
    {
        return datos.vacia();
    }


    /**
     * Agrega un nuevo proceso a la Cola de Prioridad.
     * @param proceso Proceso a ser agregado a la cola.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el proceso, y <b>false</b> en caso contrario.
     */
    public boolean agregar(Proceso proceso)
    {
        if(datos.vacia())
        {
            return datos.agregar(proceso);
        }
        else if(!datos.vacia())
        {
            if(tipoPrioridad == CriterioOrden.ASCENDENTE)
            {
                return agregarAscendente(proceso);
            }
            else
            {
               return agregarDescendente(proceso);
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Agrega un nuevo proceso a la Cola de Prioridad en formato
     * ascendente.
     * @param nuevoProceso Proceso a ser agregado a la cola.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el proceso, y <b>false</b> en caso contrario.
     */
    private boolean agregarAscendente(Proceso nuevoProceso)
    {
        // 1.- Se agrega el proceso a la cola.
        if(datos.agregar(nuevoProceso))
        {
            // 2.- Se obtienen las posiciones del nuevo proceso y de su padre.
            int posNuevo = datos.getLongitud() - 1;
            int posPadre = (posNuevo - 1) / 2;
            // 3.- Se obtiene la información del proceso padre.
            Proceso procesoPadre = (Proceso) datos.getElemento(posPadre);

            // 4.- Si el nuevo proceso es de mayor prioridad que su padre.
            if(nuevoProceso.getPrioridad() > procesoPadre.getPrioridad())
            {
                // 5.- Se sitúa el nuevo proceso en el espacio que les corresponde
                // cumpliendo la regla de que debe ser mayor a sus hijos.
                while (posPadre >= 0 && (nuevoProceso.getPrioridad() > procesoPadre.getPrioridad()) )
                {
                    datos.setElemento(posPadre, nuevoProceso);
                    datos.setElemento(posNuevo, procesoPadre);
                    posNuevo = posPadre;
                    posPadre = (posPadre - 1 ) / 2;
                    procesoPadre = (Proceso) datos.getElemento(posPadre);
                }
            }
            return true;
        }
        // 6.- Si no se pudo agregar el proceso.
        else
        {
            return false;
        }
    }

    /**
     * Agrega un nuevo proceso a la Cola de Prioridad en formato
     * descendente.
     * @param nuevoProceso Proceso a ser agregado a la cola.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el proceso, y <b>false</b> en caso contrario.
     */
    private boolean agregarDescendente(Proceso nuevoProceso)
    {
        // 1.- Se agrega el proceso a la cola.
        if(datos.agregar(nuevoProceso))
        {
            // 2.- Se obtienen las posiciones del nuevo proceso y de su padre.
            int posNuevo = datos.getLongitud() - 1;
            int posPadre = (posNuevo - 1) / 2;
            // 3.- Se obtiene la información del proceso padre.
            Proceso procesoPadre = (Proceso) datos.getElemento(posPadre);

            // 4.- Si el nuevo proceso es de menor prioridad que su padre.
            if(nuevoProceso.getPrioridad() < procesoPadre.getPrioridad())
            {
                // 5.- Se sitúa el nuevo proceso en el espacio que les corresponde
                // cumpliendo la regla de que debe ser menor a sus hijos.
                while (posPadre >= 0 && (nuevoProceso.getPrioridad() < procesoPadre.getPrioridad()) )
                {
                    datos.setElemento(posPadre, nuevoProceso);
                    datos.setElemento(posNuevo, procesoPadre);
                    posNuevo = posPadre;
                    posPadre = (posPadre - 1 ) / 2;
                    procesoPadre = (Proceso) datos.getElemento(posPadre);
                }
            }
            return true;
        }
        // 6.- Si no se pudo agregar el proceso.
        else
        {
            return false;
        }
    }

    /**
     * Realiza la impresión en la salida estandar de la
     * cola de prioridad.
     */
    public void imprimir()
    {
        for(int posicion = 0; posicion < datos.capacidad(); posicion++)
        {
            SalidaEstandar.consola(""+ datos.getElemento(posicion) + " ");
        }
    }

    /**
     * Elimina el Proceso que tiene la mayor prioridad
     * dentro de la cola de prioridad.
     * @return Regresa el Proceso que fue eliminado en caso
     * de haber podido realizar la operación con éxito, o null
     * en caso contrario.
     */
    public Proceso eliminar()
    {
        if(!datos.vacia())
        {
            if(tipoPrioridad == CriterioOrden.ASCENDENTE)
            {
                return eliminarAscendente();
            }
            else
            {
                return eliminarDescendente();
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Elimina el proceso con mayor prioridad contenido
     * en la cola de prioridad en formato ascendente.
     * @return Regresa el proceso que fue eliminado.
     */
    private Proceso eliminarAscendente()
    {
        // 1.- Se obtiene el proceso siguiente a atenderse en la cola.
        Proceso procesoPrioritario = (Proceso) datos.getElemento(0);

        // 2.- Si la longitud de la cola es uno, entonces se regresa tal
        // cual el proceso.
        if(datos.getLongitud() == 1)
        {
            datos.eliminarTope();
            return procesoPrioritario;
        }
        // 3.- Si la longitud de la cola es de dos, entonces se pasa el segundo
        // elemento al próximo proceso a atenderse y se regresa el que estaba primero
        // a atenderse.
        else if(datos.getLongitud() == 2)
        {
            datos.setElemento(0, datos.getElemento(1));
            datos.eliminarTope();
            return procesoPrioritario;
        }
        // 4.- Si la longitud de la cola es mayor a dos.
        else
        {
            // 5.- Se obtienen las posiciones del padre y sus hijos.
            int posPadre = 0;
            int posHijoIzquierdo = 1;
            int posHijoDerecho = 2;
            Proceso hijoIzquierdo = (Proceso) datos.getElemento(posHijoIzquierdo);
            Proceso hijoDerecho = (Proceso) datos.getElemento(posHijoDerecho);

            // 6.- Mientras no se haya llegado al final del monticulo.
            while (datos.getElemento(posHijoIzquierdo) != null ||
                    datos.getElemento(posHijoDerecho) != null)
            {
                // 7.- Si el hijo izquierdo es null se sube el derecho al padre actual.
                if(datos.getElemento(posHijoIzquierdo) == null)
                {
                    datos.setElemento(posPadre, hijoDerecho);
                    break;
                }
                // 8.- Si el hijo derecho es null se sube el izquierdo al padre actual.
                else if(datos.getElemento(posHijoDerecho) == null)
                {
                    datos.setElemento(posPadre, hijoIzquierdo);
                    break;
                }
                // 9.- Si el hijo izquierdo tiene mayor prioridad que el hijo
                // derecho, el hijo izquierdo pasa a ser el padre.
                else if(hijoIzquierdo.getPrioridad() > hijoDerecho.getPrioridad())
                {
                    datos.setElemento(posPadre, hijoIzquierdo);
                    posPadre = posHijoIzquierdo;
                }
                // 10.- Si el hijo derecho tiene mayor prioridad que el hijo
                // izquierdo, el hijo derecho pasa a ser el padre.
                else if(hijoDerecho.getPrioridad() > hijoIzquierdo.getPrioridad())
                {
                    datos.setElemento(posPadre, hijoDerecho);
                    posPadre = posHijoDerecho;
                }
                // 11.- Si los dos tienen las misma prioridad.
                else
                {
                    datos.setElemento(posPadre, hijoIzquierdo);
                    posPadre = posHijoIzquierdo;
                }
                // 12.- Se recalculan las posiciones de los hijos del nuevo padre
                // a tomarse en cuenta.
                posHijoIzquierdo = (2 * posPadre) + 1;
                posHijoDerecho  = (2 * posPadre) + 2;
                hijoIzquierdo = (Proceso) datos.getElemento(posHijoIzquierdo);
                hijoDerecho = (Proceso) datos.getElemento(posHijoDerecho);
            }
            // 13.- Finalmente se saca el proceso de la cola.
            datos.eliminarTope();
            return procesoPrioritario;
        }
    }

    /**
     * Elimina el proceso con mayor prioridad contenido
     * en la cola de prioridad en formato descendente.
     * @return Regresa el proceso que fue eliminado.
     */
    private Proceso eliminarDescendente()
    {
        // 1.- Se obtiene el proceso siguiente a atenderse en la cola.
        Proceso procesoPrioritario = (Proceso) datos.getElemento(0);

        // 2.- Si la longitud de la cola es uno, entonces se regresa tal
        // cual el proceso.
        if(datos.getLongitud() == 1)
        {
            datos.eliminarTope();
            return procesoPrioritario;
        }
        // 3.- Si la longitud de la cola es de dos, entonces se pasa el segundo
        // elemento al próximo proceso a atenderse y se regresa el que estaba primero
        // a atenderse.
        else if(datos.getLongitud() == 2)
        {
            datos.setElemento(0, datos.getElemento(1));
            datos.eliminarTope();
            return procesoPrioritario;
        }
        // 4.- Si la longitud de la cola es mayor a dos.
        else
        {
            // 5.- Se obtienen las posiciones del padre y sus hijos.
            int posPadre = 0;
            int posHijoIzquierdo = 1;
            int posHijoDerecho = 2;
            Proceso hijoIzquierdo = (Proceso) datos.getElemento(posHijoIzquierdo);
            Proceso hijoDerecho = (Proceso) datos.getElemento(posHijoDerecho);

            // 6.- Mientras no se haya llegado al final del monticulo.
            while (datos.getElemento(posHijoIzquierdo) != null ||
                    datos.getElemento(posHijoDerecho) != null)
            {
                // 7.- Si el hijo izquierdo es null se sube el derecho al padre actual.
                if(datos.getElemento(posHijoIzquierdo) == null)
                {
                    datos.setElemento(posPadre, hijoDerecho);
                    break;
                }
                // 8.- Si el hijo derecho es null se sube el izquierdo al padre actual.
                else if(datos.getElemento(posHijoDerecho) == null)
                {
                    datos.setElemento(posPadre, hijoIzquierdo);
                    break;
                }
                // 9.- Si el hijo izquierdo tiene mayor prioridad que el hijo
                // derecho, el hijo izquierdo pasa a ser el padre.
                else if(hijoIzquierdo.getPrioridad() < hijoDerecho.getPrioridad())
                {
                    datos.setElemento(posPadre, hijoIzquierdo);
                    posPadre = posHijoIzquierdo;
                }
                // 10.- Si el hijo derecho tiene mayor prioridad que el hijo
                // izquierdo, el hijo derecho pasa a ser el padre.
                else if(hijoDerecho.getPrioridad() < hijoIzquierdo.getPrioridad())
                {
                    datos.setElemento(posPadre, hijoDerecho);
                    posPadre = posHijoDerecho;
                }
                // 11.- Si los dos tienen las misma prioridad.
                else
                {
                    datos.setElemento(posPadre, hijoIzquierdo);
                    posPadre = posHijoIzquierdo;
                }
                // 12.- Se recalculan las posiciones de los hijos del nuevo padre
                // a tomarse en cuenta.
                posHijoIzquierdo = (2 * posPadre) + 1;
                posHijoDerecho  = (2 * posPadre) + 2;
                hijoIzquierdo = (Proceso) datos.getElemento(posHijoIzquierdo);
                hijoDerecho = (Proceso) datos.getElemento(posHijoDerecho);
            }
            // 13.- Finalmente se saca el proceso de la cola.
            datos.eliminarTope();
            return procesoPrioritario;
        }
    }
}
