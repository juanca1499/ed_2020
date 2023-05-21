package edlineal;


import entradasalida.SalidaEstandar;
import utilidades.ConversionMatriz;

/**
 * Esta clase define el TDA Matriz.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Matriz
{
    protected Object[][] datos;
    protected int RENG;
    protected int COL;

    /**
     * Define el constructor de TDA Matriz.
     * @param reng Cantidad de filas que tendrá la matriz.
     * @param col Cantidad de columnas que tendrá la matriz.
     */
    public Matriz(int reng, int col)
    {
        RENG = reng;
        COL = col;
        datos = new Object[reng][col];
    }

    public boolean validarDim(int valor, int tam)
    {
        if(valor >= 0 && valor < tam)
        {
            return true;
        }
        return false;
    }

    /**
     * Verifica si las dimensiones de la Matriz pasada
     * como parámetro es equivalente a las dimensiones
     * de la matriz <b>datos[][]</b> contenida en el
     * objeto.
     * @param matriz2 Matriz a verificar si es equivalente
     *                en sus dimensiones.
     * @return Regresa <b>true</b> en caso de sí ser del mismo
     * tamaño, o <b>false</b> en caso contrario.
     */
    protected boolean validarDim(Matriz matriz2)
    {
        if(RENG == matriz2.numRenglones() && COL == matriz2.numColumnas())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Object getElemento(int reng, int col)
    {
        if (validarDim(reng, RENG) && validarDim(col, COL))
        {
            return datos[reng][col];
        }
        return null;
    }

    public boolean setElemento(int reng, int col, Object valor)
    {
        if(valor != null && validarDim(reng, RENG) && validarDim(col, COL))
        {
            datos[reng][col] = valor;
            return true;
        }
        return false;
    }

    public int numRenglones()
    {
        return RENG;
    }

    public int numColumnas()
    {
        return COL;
    }

    public void imprimir()
    {
        //Sacar las rebanadas de jamón
        for(int renglon = 0; renglon < RENG; renglon++)
        {
            for(int columna = 0; columna < COL; columna++)
            {
                SalidaEstandar.consola(datos[renglon][columna]+" ");
            }
            SalidaEstandar.consola("\n");
        }
        SalidaEstandar.consola("\n");
    }

    /**
     * Invierte los elementos contenidos en la matriz,
     * cambiando las filas por las columnas y las columnas
     * por las filas.
     */
    public void aplicarTraspuesta()
    {
        Matriz copiaMatriz = copiar();
        datos = new Object[COL][RENG];
        int tempCol = COL;
        COL = RENG;
        RENG = tempCol;

        for (int fila = 0; fila < RENG; fila++)
        {
            for (int columna = 0; columna < COL; columna++)
            {
                datos[fila][columna] = copiaMatriz.getElemento(columna, fila);
            }
        }
    }

    public void inicializar(Object valor)
    {
        for(int renglon = 0; renglon < RENG; renglon++)
        {
            for(int columna = 0; columna < COL; columna++)
            {
                datos[renglon][columna] = valor;
            }
        }
    }

    /**
     * Asigna un mismo elemento a toda la diagonal principal
     * de la matriz. La matriz tiene que ser cuadrada para
     * poder tener una diagonal principal.
     * @param valor Elemento a ser asignado.
     */
    public void rellenarDiagonal(Object valor)
    {
        if(RENG == COL)
        {
            for(int index = 0; index < RENG; index++)
            {
                datos[index][index] = valor;
            }
        }
    }

    /**
     * Asigna un mismo elemento a toda la diagonal principal
     * de la matriz. La matriz tiene que ser cuadrada para
     * poder tener una diagonal principal.
     * @param vector Arreglo con los elementos a ser asignados.
     */
    public void rellenarDiagonal(Arreglo vector)
    {
        if(RENG == COL)
        {
            for(int index = 0; index < RENG; index++)
            {
                datos[index][index] = vector.getElemento(index);
            }
        }
    }

    /**
     * Genera una nueva matriz con las mismas dimensiones
     * y valores que <b>datos[][]</b>.
     * @return Regresa la copia de la Matriz.
     */
    public Matriz copiar()
    {
        Matriz matrizCopia = new Matriz(RENG, COL);

        for (int fila = 0; fila < RENG; fila++)
        {
            for (int columna = 0; columna < COL; columna++)
            {
                matrizCopia.setElemento(fila, columna, datos[fila][columna]);
            }
        }
        return matrizCopia;
    }

    /**
     * Comprueba si las dos matrices son iguales en
     * todos sus elementos.
     * @param matriz2 Datos a compararse con la interna de la
     *                clase Matriz.
     * @return Regresa <b>true</b> en caso de que todos los elementos
     * sean iguales, o <b>false</b> en caso contrario.
     */
    public boolean esIgual(Matriz matriz2)
    {
        boolean sonIguales = false;

        if(validarDim(matriz2))
        {
            for (int fila = 0; fila < RENG; fila++)
            {
                for (int columna = 0; columna < COL; columna++)
                {
                    if (datos[fila][columna].equals(matriz2.getElemento(fila, columna)))
                    {
                        sonIguales = true;
                    }
                    else
                    {
                        sonIguales = false;
                        break;
                    }
                }
            }
        }
        return sonIguales;
    }

    /**
     * Elimina los datos existentes en la Matriz y crea un
     * vector con 1 columna de longitud, con el número de
     * renglones indicado como parámetro. Además, agrega
     * a todas las posiciones el Object dado como argumento.
     * @param numRenglones Longitud en renglones del nuevo
     *                     vector a crear.
     * @param valor Elemento a ser insertado en todas las
     *              posiciones del nuevo vector.
     * @return Regresa <b>true</b> en caso de realizar la
     * operación con éxito, o <b>false</b> en caso contrario.
     */
    public boolean vectorCol(int numRenglones, Object valor)
    {
        if(valor != null && numRenglones > 0)
        {
            COL = 1;
            RENG = numRenglones;
            datos = new Object[numRenglones][1];
            this.inicializar(valor);
            return true;
        }
        return false;
    }

    /**
     * Elimina los datos existentes en la Matriz y crea un
     * vector con 1 renglón de longitud, con el número de
     * columnas indicado como parámetro. Además, agrega
     * a todas las posiciones el Object dado como argumento.
     * @param numColumnas Longitud en columnas del nuevo
     *                    vector a crear.
     * @param valor Elemento a ser insertado en todas las
     *              posiciones del nuevo vector.
     * @return <b>true</b> en caso de realizar la
     * operación con éxito, o <b>false</b> en caso contrario.
     */
    public boolean vectorReng(int numColumnas, Object valor)
    {
        if(valor != null && numColumnas > 0)
        {
            COL = numColumnas;
            RENG = 1;
            datos = new Object[1][numColumnas];
            this.inicializar(valor);
            return true;
        }
        return false;
    }

    /**
     * Recrea la matriz interna del objeto, asignándole los
     * elementos y dimensión
     * @param matriz2 Nueva matriz a ser asigana a la interna
     *                del objeto.
     * @return Regresa <b>true</b> en caso de haber podido
     * realizar con éxito el procedimiento, o <b>false</b>
     * en caso contrario.
     */
    public boolean definirMatriz(Matriz matriz2)
    {
        if(matriz2 != null)
        {
            RENG = matriz2.numRenglones();
            COL = matriz2.numColumnas();
            datos = new Object[RENG][COL];
            this.copiarMatriz(matriz2);
            return true;
        }
        return false;
    }

    /**
     * De la matriz dada como parametro, realiza una copia de los valores
     * de la misma asignándolos al arreglo de objetos interno <b>datos[][]</b>
     * @param matriz Valores a ser copiados.
     */
    private void copiarMatriz(Matriz matriz)
    {
        if(validarDim(matriz))
        {
            for (int fila = 0; fila < RENG; fila++)
            {
                for (int columna = 0; columna < COL; columna++)
                {
                    datos[fila][columna] = matriz.getElemento(fila, columna);
                }
            }
        }
    }

    /**
     * Añade a la matriz actual un renglón extra a los que
     * ya poseía. Para que dicho proceso pueda realizarse
     * el arreglo dado como parámetro tiene que tener la
     * misma longitud que las columnas de las matriz original.
     * @param arreglo Valores a ser agregados en un nuevo
     *                renglón de la matriz.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * la operación, o <b>false</b> en caso contrario.
     */
    public boolean agregarRenglon(Arreglo arreglo)
    {
        if(arreglo != null && arreglo.capacidad() == COL)
        {
            Matriz matrizCopia = copiar();
            datos = new Object[RENG + 1][COL];
            this.copiarMatriz(matrizCopia);
            RENG += 1;

            for(int columna = 0; columna < COL; columna++)
            {
                datos[RENG - 1][columna] = arreglo.getElemento(columna);
            }
            return true;
        }
        return false;
    }

    /**
     * Añade a la matriz actual una columna extra a las que
     * ya poseía. Para que dicho proceso pueda realizarse
     * el arreglo dado como parámetro tiene que tener la
     * misma longitud que las filas de las matriz original.
     * @param arreglo Valores a ser agregados en una nueva
     *                columna de la matriz.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * la operación, o <b>false</b> en caso contrario.
     */
    public boolean agregarColumna(Arreglo arreglo)
    {
        if(arreglo != null && arreglo.capacidad() == RENG)
        {
            Matriz matrizCopia = copiar();
            datos = new Object[RENG][COL + 1];
            this.copiarMatriz(matrizCopia);
            COL += 1;

            for(int fila = 0; fila < RENG; fila++)
            {
                datos[fila][COL - 1] = arreglo.getElemento(fila);
            }
            return true;
        }
        return false;
    }

    /**
     * Redimensiona la matriz interna del objeto agregandóle
     * del lado de las columnas la matriz dada como parámetro.
     * Para que este procedimiento pueda realizarse, la matriz
     * del argumento debe contar con la misma cantidad de renglones
     * que la original.
     * @param matriz2 Matriz a ser anexada a la interna del objeto.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * el procedimiento, o <b>false</b> en caso contrario.
     */
    public boolean agregarMatrizColumna(Matriz matriz2)
    {
        if(matriz2 != null && RENG == matriz2.numRenglones())
        {
            Matriz matrizCopia = copiar();
            datos = new Object[RENG][COL + matriz2.numColumnas()];
            this.copiarMatriz(matrizCopia);
            int antiguasColumnas = COL;
            COL += matriz2.numColumnas();

            for(int fila = 0; fila < RENG; fila++)
            {
                for(int columna = 0; columna < matriz2.numColumnas(); columna++)
                {
                    datos[fila][antiguasColumnas + columna] = matriz2.getElemento(fila, columna);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Redimensiona la matriz interna del objeto agregandóle
     * del lado de los renglones la matriz dada como parámetro.
     * Para que este procedimiento pueda realizarse, la matriz
     * del argumento debe contar con la misma cantidad de columnas
     * que la original.
     * @param matriz2 Matriz a ser anexada a la interna del objeto.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * el procedimiento, o <b>false</b> en caso contrario.
     */
    public boolean agregarMatrizRenglon(Matriz matriz2)
    {
        if(matriz2 != null && COL == matriz2.numColumnas())
        {
            Matriz matrizCopia = copiar();
            datos = new Object[RENG + matriz2.numRenglones()][COL];
            this.copiarMatriz(matrizCopia);
            int antiguosRenglones = RENG;
            RENG += matriz2.numRenglones();

            for(int fila = 0; fila < matriz2.numRenglones(); fila++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    datos[fila + antiguosRenglones][columna] = matriz2.getElemento(fila, columna);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Crea un TDA de tipo Matriz3D a partir de la matriz
     * ya contenida en el objeto, y agregando las matrices
     * enviadas como parámetro. Para que este proceso pueda
     * ser efectuado las matrices deben tener las mismas
     * dimensiones.
     * @param matrices Matrices a formar parte de la Matriz3D.
     * @return Regresa una <b>Matriz3D</b> en caso de haber podido
     * efectuar el procedimiento, o <b>null</b> en caso contrario.
     */
    public Matriz3D aMatriz3D(Arreglo matrices)
    {
        for(int contador = 0; contador < matrices.getLongitud(); contador++)
        {
            if(!this.validarDim( (Matriz)matrices.getElemento(contador) ))
            {
                return null;
            }
        }

        int prof = matrices.getLongitud() + 1;
        Matriz3D matriz3D = new Matriz3D(RENG, COL, prof);

        for(int profundidad = 0; profundidad < prof; profundidad++)
        {
            for(int fila = 0; fila < RENG; fila++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    if(profundidad == 0)
                    {
                        matriz3D.setElemento(fila, columna, profundidad, datos[fila][columna]);
                    }
                    else
                    {
                        Matriz matrizTemporal = (Matriz) matrices.getElemento(profundidad - 1);
                        matriz3D.setElemento(fila, columna, profundidad,
                                            matrizTemporal.getElemento(fila, columna));
                    }
                }
            }

        }
        return matriz3D;
    }


    /**
     * Invierte las columnas de la matriz. De esta manera
     * las últimas columnas ahora serán las primeras y viceversa.
     */
    public void invertirColumnas()
    {
        Matriz copiaMatriz = copiar();

        int columna = 0;
        for(int columnaInversa = COL - 1; columnaInversa >= 0; columnaInversa--)
        {
            for(int fila = 0; fila < RENG; fila++)
            {
                datos[fila][columnaInversa] = copiaMatriz.getElemento(fila, columna);
            }
            columna++;
        }
    }

    /**
     * Invierte las filas de la matriz. De esta manera la últimas
     * filas ahora será las primeras y viceversa.
     */
    public void invertirFilas()
    {
        Matriz copiaMatriz = copiar();

        int fila = 0;
        for(int filaInversa = RENG - 1; filaInversa >= 0; filaInversa--)
        {
            for(int columna = 0; columna < COL; columna++)
            {
                datos[filaInversa][columna] = copiaMatriz.getElemento(fila, columna);
            }
            fila++;
        }
    }

    /**
     * Convierte la matriz a un arreglo.
     * @param tipoConversion Criterio de conversión: ya sea
     *                       en base a las columnas, o las
     *                       filas.
     * @return Regresa un Arreglo con los datos de la matriz.
     */
    public Arreglo reDim(ConversionMatriz tipoConversion)
    {
        Arreglo arreglo = new Arreglo(COL * RENG);

        // Se convertirá la Matriz en base a sus renglones.
        if(tipoConversion == ConversionMatriz.POR_RENGLON)
        {
            reDimPorRenglon(0, 0, arreglo);
            return arreglo;
        }
        // Se convertirá la Matriz en base a sus columnas.
        else if(tipoConversion == ConversionMatriz.POR_COLUMNA)
        {
            reDimPorColumna(0,0, arreglo);
            return arreglo;
        }
        // No se eligió una opción válida.
        else
        {
            return arreglo;
        }
    }

    /**
     * Copia los datos de la matriz a un arreglo en base
     * a los renglones.
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la matriz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void reDimPorRenglon(int reng, int col, Arreglo arreglo)
    {
        // Caso recursivo.
        if(reng < RENG)
        {
            // Aún hay columnas.
            if(col < COL)
            {
                arreglo.agregar(datos[reng][col]);
                reDimPorRenglon(reng, col + 1, arreglo);
            }
            // Ya no hay columnas.
            else
            {
                reng++;
                col = 0;
                // Aún no se sobrepasa el límite de renglones.
                if(reng != RENG)
                {
                    arreglo.agregar(datos[reng][col]);
                    reDimPorRenglon(reng, col + 1, arreglo);
                }
            }
        }
        //Caso base escondido.
    }

    /**
     * Copia los datos de la matriz a un arreglo en base
     * a las columnas
     * @param reng Contador de renglones de la matriz.
     * @param col Contador de columnas de la matriz.
     * @param arreglo Arreglo al que se le agregan los datos.
     */
    private void reDimPorColumna(int reng, int col, Arreglo arreglo)
    {
        // Caso recursivo.
        if(col < COL)
        {
            // Aún hay renglones.
            if(reng < RENG)
            {
                arreglo.agregar(datos[reng][col]);
                reDimPorColumna(reng + 1, col, arreglo);
            }
            // Ya no hay renglones.
            else
            {
                reng = 0;
                col++;
                // Aún no se sobrepasa el límite de columnas.
                if(col != COL)
                {
                    arreglo.agregar(datos[reng][col]);
                    reDimPorColumna(reng + 1, col, arreglo);
                }
            }
        }
        //Caso base escondido.
    }

    /**
     * Suprime un renglón de la matriz y recorre las filas
     * posteriores a ésta una posición atrás.
     * @param renglon Índice del renglón a ser eliminado.
     * @return Regresa <b>true</b> en caso de haber podido
     * realizar la operación, o <b>false</b> en caso contrario.
     */
    public boolean eliminarRenglon(int renglon)
    {
        if(renglon >= 0 && renglon < RENG)
        {// El renglón tiene una posición válida en la matriz.

            // Partimos de la fila siguiente a borrar y recorremos
            // los datos de las que están delante.
            for(int numRenglon = (renglon + 1); numRenglon < RENG; numRenglon++)
            {
                for(int numColumna = 0; numColumna < COL; numColumna++)
                {
                    datos[numRenglon - 1][numColumna] = datos[numRenglon][numColumna];
                }
            }
            // Decrementamos el número de renglones de la matriz.
            RENG--;
            return true;
        }
        else
        {// El renglón no tiene una posición válida en la matriz.
            return false;
        }
    }

    /**
     * Suprime una columna de la matriz y recorre las columnas
     * posteriores a ésta una posición atrás.
     * @param columna Índice de la columna a ser eliminada.
     * @return Regresa <b>true</b> en caso de haber podido
     * realizar la operación, o <b>false</b> en caso contrario.
     */
    public boolean eliminarColumna(int columna)
    {
        if(columna >= 0 && columna < COL)
        {// La columna tiene una posición válida en la matriz.

            // Partimos de la columna siguiente a borrar y recorremos
            // los datos de las que están delante.
            for(int numRenglon = 0; numRenglon < RENG; numRenglon++)
            {
                for(int numColumna = (columna + 1); numColumna < COL; numColumna++)
                {
                    datos[numRenglon][numColumna - 1] = datos[numRenglon][numColumna];
                }
            }
            // Decrementamos el número de columnas de la matriz.
            COL--;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Extrae una copia de los elementos de un
     * renglón de la matriz.
     * @param numRenglon Fila a ser copiada.
     * @return Regresa un arreglo con los datos
     * copiados en caso de haberse podido realizar
     * la operación, o null en caso contrario.
     */
    public Arreglo copiarRenglon(int numRenglon)
    {
        if(numRenglon >= 0 && numRenglon < RENG)
        {// El índice del renglón es válido.

            Arreglo renglonMatriz = new Arreglo(COL);

            // Se copian los datos del renglón.
            for(int columna = 0; columna < COL; columna++)
            {
                renglonMatriz.agregar(datos[numRenglon][columna]);
            }
            return renglonMatriz;
        }
        else
        {// El índice del renglón es inválido.
            return null;
        }
    }

    /**
     * Obtiene los datos contenidos en la diagonal
     * principal de la matriz.
     * @return Regresa un vector con los datos
     * obtenidos.
     */
    public Arreglo extraerDiagonalPrincipal()
    {
        Arreglo diagonal = new Arreglo(RENG);

        // Se recorren las posiciones de la matriz.
        for(int renglon = 0; renglon < RENG; renglon++)
        {
            for(int columna = 0; columna < COL; columna++)
            {
                if(renglon == columna)
                {// Es una posición de la diagonal principal.
                    // Se agrega el dato al vector.
                    diagonal.agregar(datos[renglon][columna]);
                }
            }
        }
        return diagonal;
    }
}
