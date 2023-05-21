package vistas;

import utilidades.TipoCorrelacion;

/**
 * Esta clase implementa el TDA PantallaEstadistica.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PantallaEstadistica
{
    /**
     * Interpreta un valor de correlación para indicar si existe
     * o no. En caso de sí existir indica de qué tipo es.
     * @param correlacion Valor de correlación a ser interpretado.
     * @return Regresa un enum TipoCorrelacion con el equivalente de
     * la correlacion dada como parámetro.
     */
    public static TipoCorrelacion getTipoCorrelacion(double correlacion)
    {
        if(correlacion == 0)
        {
            return TipoCorrelacion.NINGUNA;
        }
        else if(correlacion == 1)
        {
            return TipoCorrelacion.POSITIVA_PERFECTA;
        }
        else if(correlacion > 0 && correlacion < 1)
        {
            return TipoCorrelacion.POSITIVA;
        }
        else if(correlacion == -1)
        {
            return TipoCorrelacion.NEGATIVA_PERFECTA;
        }
        else if(correlacion > -1 && correlacion < 0)
        {
            return TipoCorrelacion.NEGATIVA;
        }
        else
        {
            return TipoCorrelacion.INVALIDA;
        }
    }
}
