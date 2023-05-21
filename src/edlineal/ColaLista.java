package edlineal;

import entradasalida.SalidaEstandar;

/**
 * Esta clase implementa el TDA ColaLista.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ColaLista implements ICola
{
    /** Guarda el contenido de los elementos de la ColaLista. */
    private ListaLigada datosCola;

    /**
     * Define el constructor de TDA ColaLista.
     */
    public ColaLista()
    {
        datosCola = new ListaLigada();
    }

    @Override
    public boolean vacia()
    {
        return datosCola.vacia();
    }

    @Override
    public boolean llena()
    {
        return datosCola.llena();
    }

    @Override
    public boolean agregar(Object valor)
    {
        return datosCola.agregar(valor);
    }

    @Override
    public Object eliminar()
    {
        return datosCola.eliminarInicio();
    }

    @Override
    public void imprimir()
    {
        datosCola.imprimir();
    }
}
