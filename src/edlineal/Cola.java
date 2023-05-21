package edlineal;

import entradasalida.SalidaEstandar;

/**
 * Esta clase implementa el TDA Cola.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Cola implements ICola
{
    /** Guarda el contenido de los elementos de la Cola. */
    protected Object datosCola[];
    /** Guarda el tamaño de la Cola. */
    protected final int MAX;
    /** Guarda la posición en la Cola del primer elemento insertado. */
    protected int primero;
    /** Guarda la posición en la Cola del último elemento insertado. */
    protected int ultimo;

     /**
     * Define el constructor de TDA Cola.
     * @param tam Es el tamaño que tendrá la cola.
     */
    public Cola(int tam)
    {
        MAX = tam;
        datosCola = new Object[MAX];
        primero = -1;
        ultimo = -1;
    }

    @Override
    public boolean vacia()
    {
        if(primero == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean llena()
    {
        if((primero == 0 && ultimo == (MAX - 1) ) || (primero == ultimo + 1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean agregar(Object valor)
    {
        if(!llena())
        {
            if(vacia())
            {
                primero = 0;
                ultimo = 0;
            }
            else if(ultimo == (MAX - 1) )
            {
                ultimo = 0;
            }
            else
            {
                ultimo++;
            }
            datosCola[ultimo] = valor;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Object eliminar()
    {
        if(!vacia())
        {
            Object contenido = datosCola[primero];
            if(primero == ultimo)
            {
                primero = -1;
                ultimo = -1;
            }
            else if(primero == (MAX - 1) )
            {
                primero = 0;
            }
            else
            {
                primero++;
            }
            return contenido;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void imprimir()
    {
        if(primero <= ultimo)
        {
            for(int mov = primero; mov <= ultimo; mov++)
            {
                SalidaEstandar.consola("" + datosCola[mov] + " ");
            }
        }
        else
        {
            for(int mov = primero; mov <= (MAX - 1); mov++)
            {
                SalidaEstandar.consola("" + datosCola[mov] + " ");
            }
            for(int mov = 0; mov <= ultimo; mov++)
            {
                SalidaEstandar.consola("" + datosCola[mov] + " ");
            }
        }
    }
}
