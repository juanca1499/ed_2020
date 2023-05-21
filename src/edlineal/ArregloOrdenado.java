package edlineal;


import utilidades.Comparador;
import utilidades.CriterioOrden;


/**
 * Esta clase implementa el TDA ArregloOrdenado.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ArregloOrdenado extends Arreglo
{
    private CriterioOrden orden;

    /**
     * Define el constructor de TDA ArregloOrdenado.
     * @param tam Es el tamaño para crear el Arreglo.
     * @param orden Es el orden en que se van a almacenar
     *              los elementos: ascendente o descendente.
     */
    public ArregloOrdenado(int tam, CriterioOrden orden)
    {
        super(tam);
        this.orden = orden;
    }

    @Override
    public boolean agregar(Object valor)
    {
        int posicion = (int) buscar(valor);

        if(posicion > 0 || llena() || valor == null)
        {
            return false;
        }
        else
        {
            posicion = posicion * -1;
            posicion--;
            tope++;

            for(int contador = tope; contador > posicion; contador--)
            {
                datos[contador] = datos[contador - 1];
            }
            datos[posicion] = valor;
            return true;
        }
    }

    @Override
    public Object buscar(Object valor)
    {
        int posicion = 0;

        if(orden == CriterioOrden.ASCENDENTE && valor != null)
        {
            while (posicion <= tope && Comparador.comparar(valor, datos[posicion]) > 0)
            {
                posicion++;
            }
            if (posicion > tope || Comparador.comparar(valor, datos[posicion]) < 0)
            {
                return (posicion + 1) * -1;
            }
            else
            {
                return posicion + 1;
            }
        }
        else
        {
            while (posicion <= tope && Comparador.comparar(valor, datos[posicion]) < 0)
            {
                posicion++;
            }
            if(posicion > tope || Comparador.comparar(valor, datos[posicion]) > 0)
            {
                return (posicion + 1) * -1;
            }
            else
            {
                return posicion + 1;
            }
        }
    }

    @Override
    public boolean setElemento(Object valorV, Object valorN, int numOcurrencias)
    {
        if(valorV != null && valorN != null)
        {
            Integer posicion = (Integer) buscar(valorV);

            if(posicion > 0)
            {
                this.eliminar(posicion - 1);
                this.agregar(valorN);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setElemento(int pos, Object valor)
    {
        if(posicionValida(pos) && valor != null)
        {
            Integer posicion = pos;
            this.eliminar(posicion - 1);
            this.agregar(valor);
            return true;
        }
        return false;
    }

    @Override
    public Object eliminar(Object valor)
    {
        if(!vacia() && valor != null)
        {
            int posicion = (int) this.buscar(valor);

            if(posicion > 0)
            {
                posicion--;
                tope--;
                Object contenido = datos[posicion];
                
                for(int contador = posicion; contador <= tope; contador++)
                {
                    datos[contador] = datos[contador + 1];
                }
                return contenido;
            }
            else
            {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean agregarArreglo(Arreglo arreglo2)
    {
        boolean agregado = false;
        boolean continuarAgregando = false;

        for(int contador = 0; contador <= arreglo2.getLongitud(); contador++)
        {
            if(arreglo2.getElemento(contador) != null)
            {
                continuarAgregando = this.agregar(arreglo2.getElemento(contador));
            }
            if(!continuarAgregando)
            {
                break;
            }
            agregado = true;
        }
        return agregado;
    }

    @Override
    public void invertir()
    {
        if(this.orden == CriterioOrden.ASCENDENTE)
        {
            this.orden = CriterioOrden.DESCENDENTE;
        }
        else
        {
            this.orden = CriterioOrden.ASCENDENTE;
        }
        super.invertir();
    }

    @Override
    public void rellenar(Object valor, int cuantos)
    {
        if(valor != null)
        {
            this.agregar(valor);
        }
    }
}
