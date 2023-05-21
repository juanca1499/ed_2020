package edlineal;

import catalogos.Proceso;
import entradasalida.SalidaEstandar;
import utilidades.ConversionMatriz;

/**
 * Esta clase define el TDA VectorNum.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Matriz3D
{
    protected Object[][][] datos;
    protected final int RENG;
    protected final int COL;
    protected final int PROF;

    public Matriz3D(int reng, int col, int prof)
    {
        RENG = reng;
        COL = col;
        PROF = prof;
        datos = new Object[reng][col][prof];
    }

    public void inicializar(Object valor)
    {
        for(int renglon = 0; renglon < RENG; renglon++)
        {
            for(int columna = 0; columna < COL; columna++)
            {
                for(int prof = 0; prof < PROF; prof++)
                {
                    datos[renglon][columna][prof] = valor;
                }
            }
        }
    }

    public boolean validarDim(int valor, int tam)
    {
        if(valor >= 0 && valor < tam)
        {
            return true;
        }
        return false;
    }

    public boolean setElemento(int reng, int col, int prof, Object valor)
    {
        if(valor != null && validarDim(reng, RENG) &&
            validarDim(col, COL) && validarDim(prof, PROF))
        {
            datos[reng][col][prof] = valor;
            return true;
        }
        return false;
    }

    public Object getElemento(int reng, int col, int prof) {

        if (validarDim(reng, RENG) && validarDim(col, COL) &&
                validarDim(prof, PROF))
        {
            return datos[reng][col][prof];
        }
        return null;
    }

    public void imprimir()
    {
        //Sacar las rebanadas de jamón
        for(int profundidad = 0; profundidad < PROF; profundidad++)
        {
            for(int renglon = 0; renglon < RENG; renglon++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    SalidaEstandar.consola(datos[renglon][columna][profundidad]+" ");
                }
                SalidaEstandar.consola("\n");
            }
            SalidaEstandar.consola("\n");
        }
    }

    public int renglones()
    {
        return RENG;
    }

    public int columnas()
    {
        return COL;
    }

    public int profundidad()
    {
        return PROF;
    }

    /**
     * Este método regresa las rebanadas (la dimensión Z) en forma de
     * matrices, y lo guarda en un arreglo.
     * @return Regresa un arreglo con todas y cada una de las matrices 2D
     * que tiene como rebanadas.
     */
    public Arreglo aMatriz()
    {
        Arreglo matrices = new Arreglo(PROF);

        //Por cada rebanada voy a agarrar la matriz y pasarla a una matriz.
        for(int profundidad = 0; profundidad < PROF; profundidad++)
        {
            Matriz rebanada = new Matriz(RENG, COL);
            for(int renglon = 0; renglon < RENG; renglon++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    Object contenidoCelda = datos[renglon][columna][profundidad];
                    rebanada.setElemento(renglon, columna, contenidoCelda);
                }
            }//Ya acabe cada rebanada
            matrices.agregar(rebanada);
        }
        return matrices;
    }

    /**
     * Convierte la matriz a un arreglo.
     * @param tipoConversion Criterio de conversión: ya sea
     *                       en base a las columnas, o las
     *                       filas o la profundidad.
     * @return Regresa un Arreglo con los datos de la matriz.
     */
    public Arreglo reDim(ConversionMatriz tipoConversion)
    {
        Arreglo arreglo = new Arreglo(COL * RENG * PROF);

        // Se convertirá la Matriz en base a sus renglones.
        if(tipoConversion == ConversionMatriz.POR_RENGLON)
        {
            reDimReng(0,0,0,arreglo);
            return arreglo;
        }
        // Se convertirá la Matriz en base a sus columnas.
        else if(tipoConversion == ConversionMatriz.POR_COLUMNA)
        {
            reDimCol(0,0,0, arreglo);
            return arreglo;
        }
        // Se convertirá la Matriz en base a su profundidad.
        else if(tipoConversion == ConversionMatriz.POR_PROFUNDIDAD)
        {
            reDimProf(0,0,0, arreglo);
            return arreglo;
        }
        // No se eligió una opción válida.
        else
        {
            return arreglo;
        }
    }

    /**
     * Convierte la matriz3D a un Arreglo de Matrices2D
     * @return Regresa un Arreglo de matrices según la
     * profundidad de la Matriz3D.
     */
    public Arreglo redimMat()
    {
        Arreglo matrices = new Arreglo(PROF);

        // La Matriz tiene elementos.
        if(RENG != 0 && COL != 0 && PROF != 0)
        {
            return redimMat(0, matrices);
        }
        // La Matriz está vacía.
        else
        {
            return matrices;
        }
    }

    /**
     * Función real que convierte la matriz3D a un Arreglo de
     * Matrices2D de forma recursiva.
     * @param prof Rebanada que se está copiando.
     * @param arreglo Arreglo que almacena las matrices2D obtenidas.
     * @return Regresa un Arreglo de matrices según la
     * profundidad de la Matriz3D.
     */
    private Arreglo redimMat(int prof, Arreglo arreglo)
    {
        // Caso base.
        if(prof == PROF)
        {
            return arreglo;
        }
        // Caso recursivo.
        else
        {
            arreglo.agregar(getRebanada(0,0, prof, new Matriz(RENG,COL)));
            return redimMat(prof + 1, arreglo);
        }
    }

    /**
     * Copia una rebanada o profunidad de la Matriz3D
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la matriz.
     * @param prof Contador de la profundidad de la matriz.
     * @param matriz Matriz donde se está almacenando los elementos
     *               de la rebanada o profundidad en curso.
     * @return Regresa una Matriz2D con los elementos de la rebanada.
     */
    private Matriz getRebanada(int reng, int col, int prof, Matriz matriz)
    {
        // Caso base.
        if(reng == RENG)
        {
            return matriz;
        }
        // Caso recursivo.
        else
        {
            // Aún hay columnas.
            if(col < COL)
            {
                matriz.setElemento(reng, col, datos[reng][col][prof]);
                return getRebanada(reng, col + 1, prof, matriz);
            }
            // Ya no hay columnas.
            else
            {
                reng++;
                col = 0;
                // Aún no se sobrepasa el límite de renglones.
                if(reng != RENG)
                {
                    matriz.setElemento(reng, col, datos[reng][col][prof]);
                    return getRebanada(reng, col + 1, prof, matriz);
                }
            }
        }
        return matriz;
    }

    /**
     * Copia los datos de la matriz a un arreglo en base
     * a los renglones.
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la matriz.
     * @param prof Contador de la profundidad de la matriz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void reDimReng(int reng, int col, int prof, Arreglo arreglo)
    {
        if(reng < RENG)
        {
            if(prof < PROF)
            {
                copiarCol(reng, col, prof, arreglo);
                reDimReng(reng, col, prof + 1, arreglo);
            }
            reDimReng(reng + 1, col, 0, arreglo);
        }
    }

    /**
     * Copia los datos de la matriz a un arreglo en base
     * a las columnas.
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la matriz.
     * @param prof Contador de la profundidad de la matriz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void reDimCol(int reng, int col, int prof, Arreglo arreglo)
    {
        if(col < COL)
        {
            if(prof < PROF)
            {
                copiarReng(reng, col, prof, arreglo);
                reDimCol(reng, col, prof + 1, arreglo);
            }
            reDimCol(reng, col + 1, 0, arreglo);
        }
    }

    /**
     * Copia los datos de la matriz a un arreglo en base
     * a la profundidad.
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la matriz.
     * @param prof Contador de la profundidad de la matriz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void reDimProf(int reng, int col, int prof, Arreglo arreglo)
    {
        if(prof < PROF)
        {
            if(reng < RENG)
            {
                copiarCol(reng, col, prof, arreglo);
                reDimProf(reng + 1, col, prof, arreglo);
            }
            reDimProf(0, col, prof + 1, arreglo);
        }
    }

    /**
     * Copia un renglón de la Matriz.
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la Matriz.
     * @param prof Contador de profundidad de la Mariz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void copiarReng(int reng, int col, int prof, Arreglo arreglo)
    {
        if(reng < RENG)
        {
            arreglo.agregar(datos[reng][col][prof]);
            copiarReng(reng + 1, col,  prof, arreglo);
        }
    }

    /**
     * Copia una columna de la Matriz.
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la Matriz.
     * @param prof Contador de profundidad de la Mariz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void copiarCol(int reng, int col, int prof, Arreglo arreglo)
    {
        if(col < COL)
        {
            arreglo.agregar(datos[reng][col][prof]);
            copiarCol(reng, col + 1, prof, arreglo);
        }
    }

    /**
     * Copia una rebanada de la Matriz.
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la Matriz.
     * @param prof Contador de profundidad de la Mariz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void copiarProf(int reng, int col, int prof, Arreglo arreglo)
    {
        if(prof < PROF)
        {
            arreglo.agregar(datos[reng][col][prof]);
            copiarProf(reng, col, prof + 1, arreglo);
        }
    }
}
