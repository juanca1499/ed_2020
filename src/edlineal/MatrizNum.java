package edlineal;


import matematicas.TipoLogaritmo;
import utilidades.ConversionMatriz;

/**
 * Esta clase define el TDA MatrizNum.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class MatrizNum extends Matriz
{

    /**
     * Define el constructor de TDA MatrizNum.
     * Todos los valores de la matriz son inicializados
     * en 0.
     * @param reng Cantidad de filas que tendrá la matriz.
     * @param col Cantidad de columnas que tendrá la matriz.
     */
    public MatrizNum(int reng, int col)
    {
        super(reng, col);
        super.inicializar(0.0);
    }

    @Override
    public boolean setElemento(int reng, int col, Object valor)
    {
        if(valor instanceof Number)
        {
            Double value = Double.parseDouble(valor.toString());
            return super.setElemento(reng, col, value);
        }
        return false;
    }

    @Override
    public void inicializar(Object valor)
    {
        if(valor instanceof Number)
        {
            Double value = Double.parseDouble(valor.toString());
            super.inicializar(value);
        }
    }

    /**
     * Verifica que cada uno de los elementos contenidos en la
     * matriz pasada como parámetro sean instancias de la clase
     * Number.
     * @param matriz Elementos a ser evaluados.
     * @return Regresa <b>true</b> si todos los elementos fueron
     * instancias de Number, o <b>false</b> en caso contrario.
     */
    private boolean validarElementos(Matriz matriz)
    {
        for(int fila = 0; fila < matriz.numRenglones(); fila++)
        {
            for(int columna = 0; columna < matriz.numColumnas(); columna++)
            {
                if(matriz.getElemento(fila,columna) instanceof Number)
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica que cada uno de los elementos contenidos en el
     * arreglo pasado como parámetro sean instancias de la clase
     * Number.
     * @return Regresa <b>true</b> si todos los elementos fueron
     * instancias de Number, o <b>false</b> en caso contrario.
     */
    private boolean validarElementos(Arreglo arreglo)
    {
        for(int index = 0; index < arreglo.capacidad(); index++)
        {
            if(arreglo.getElemento(index) instanceof Number)
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean esIgual(Matriz matriz2)
    {
        if(validarElementos(matriz2))
        {
            return super.esIgual(matriz2);
        }
        return false;
    }

    @Override
    public boolean vectorCol(int numRenglones, Object valor)
    {
        if(valor instanceof Number)
        {
            Double value = Double.parseDouble(valor.toString());
            return super.vectorCol(numRenglones, value);
        }
        return false;
    }

    @Override
    public boolean vectorReng(int numColumnas, Object valor)
    {
        if(valor instanceof Number)
        {
            Double value = Double.parseDouble(valor.toString());
            return super.vectorReng(numColumnas, value);
        }
        return false;
    }

    @Override
    public boolean definirMatriz(Matriz matriz2)
    {
        if(validarElementos(matriz2))
        {
            return super.definirMatriz(matriz2);
        }
        return false;
    }

    @Override
    public boolean agregarRenglon(Arreglo arreglo)
    {
        if(validarElementos(arreglo))
        {
            return super.agregarRenglon(arreglo);
        }
        return false;
    }

    @Override
    public boolean agregarColumna(Arreglo arreglo)
    {
        if(validarElementos(arreglo))
        {
            return super.agregarColumna(arreglo);
        }
        return false;
    }

    @Override
    public boolean agregarMatrizColumna(Matriz matriz2)
    {
        if(validarElementos(matriz2))
        {
            return super.agregarMatrizColumna(matriz2);
        }
        return false;
    }

    @Override
    public boolean agregarMatrizRenglon(Matriz matriz2)
    {
        if(validarElementos(matriz2))
        {
            return super.agregarMatrizRenglon(matriz2);
        }
        return false;
    }

    @Override
    public Matriz3D aMatriz3D(Arreglo matrices)
    {
        for(int index = 0; index < matrices.capacidad(); index++)
        {
            if(matrices.getElemento(index) != null)
            {
                Matriz matriz = (Matriz) matrices.getElemento(index);

                for(int fila = 0; fila < matriz.numRenglones(); fila++)
                {
                    for(int columna = 0; columna < matriz.numColumnas(); columna++)
                    {
                        if(matriz.getElemento(fila, columna) instanceof Number)
                        {
                        }
                        else
                        {
                            return null;
                        }
                    }
                }
            }
            else
            {
                return null;
            }
        }
        return super.aMatriz3D(matrices);
    }

    /**
     * Multipica todos los elementos contenidos en la matriz
     * por el argumento dado.
     * @param escalar Multiplicador de los elementos de la matriz.
     */
    public void multiplicarEscalar(double escalar)
    {
        for(int filas = 0; filas < RENG; filas++)
        {
            for(int columnas = 0; columnas < COL; columnas++)
            {
                Double multiplicando = (Double) datos[filas][columnas];
                datos[filas][columnas] = multiplicando * escalar;
            }
        }
    }

    /**
     * Realiza la multiplicación de matrices entre la contenida
     * en el objeto y la que es enviada como parámetro. Para poder
     * realizar una multiplicación de matrices, la cantidad de renglones
     * de la matriz del argumento debe ser equivalente a las columnas
     * de la interna. Además, todos los valores contenidos en ambas matrices
     * deben ser instancias de la clase Number.
     * @param matriz2 Valores a multiplicarse con los de la matriz
     *                interna.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * la operación, o <b>false</b> en caso contrario.
     */
    public boolean multiplicarMatriz(Matriz matriz2)
    {
        if(!validarElementos(matriz2))
        {
            return false;
        }

        if(COL == matriz2.numRenglones())
        {
            Matriz matriz1 = super.copiar();
            RENG = matriz1.numRenglones();
            COL = matriz2.numColumnas();
            datos = new Object[RENG][COL];

            for(int filaMatriz1 = 0; filaMatriz1 < matriz1.numRenglones(); filaMatriz1++)
            {
                for(int columnaMatriz2 = 0; columnaMatriz2 < matriz2.numColumnas(); columnaMatriz2++)
                {
                    Double suma = 0.0;
                    for(int filaMatriz2 = 0; filaMatriz2 < matriz2.numRenglones(); filaMatriz2++)
                    {
                        suma += Double.parseDouble(matriz1.getElemento(filaMatriz1, filaMatriz2).toString()) *
                                Double.parseDouble(matriz2.getElemento(filaMatriz2, columnaMatriz2).toString());
                    }
                    datos[filaMatriz1][columnaMatriz2] = suma;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Realiza la multiplicación una matriz con un vector
     * Para poder realizar la multiplicación el vector
     * debe tener el tamaño de las columnas de la matriz.
     * @param vector Valores a multiplicarse con la matriz.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * la operación, o <b>false</b> en caso contrario.
     */
    public boolean multiplicar(VectorNum vector)
    {
        if(COL == vector.getLongitud())
        {// Sí es posible realizar la multiplicación.

            Matriz matriz1 = super.copiar();
            // Se calculan las nuevas dimensiones de la matriz.
            RENG = matriz1.numRenglones();
            COL = vector.getLongitud();
            datos = new Object[RENG][COL];

            // Se iteran los renglones de la nueva matriz.
            for(int renglon = 0; renglon < matriz1.numRenglones(); renglon++)
            {
                Double suma = 0.0;
                // Se iteran las columnas de la nueva matriz que su vez
                // son las posiciones del vector.
                for(int columna = 0; columna < matriz1.numColumnas(); columna++)
                {
                    suma += Double.parseDouble(matriz1.getElemento(renglon, columna).toString()) *
                    Double.parseDouble(vector.getElemento(columna).toString());
                }
                datos[renglon][0] = suma;
            }
            return true;
        }
        else
        {// No se puede realizar la multiplicación, el vector
        //  no tiene la misma longitud que las columnas de la matriz.
            return false;
        }
    }

    /**
     * Suma los datos contenidos en la matriz.
     * @return Regresa la suma calculada.
     */
    public double sumarDatos()
    {
        double suma = 0.0;

        for(int fila = 0; fila < RENG; fila++)
        {// Se iteran las filas.

            for(int columna = 0; columna < COL; columna++)
            {// Se iteran las columnas.
             // Suma de los valores de cada posición
                suma += Double.parseDouble(datos[fila][columna].toString());
            }
        }
        return suma;
    }

    /**
     * Suma todos los elementos contenidos en la matriz con los
     * valores contenidos en la segunda matriz pasada como argumento.
     * Para que dos matrices puedan sumarse sus dimensiones tienen que
     * ser iguales.
     * @param matriz2 Valores a sumarse.
     * @return Regresa <b>true</b> en caso de haber podido realizar la suma,
     * o <b>false</b> en caso contrario.
     */
    public boolean sumarMatriz(Matriz matriz2)
    {
        if(super.validarDim(matriz2) && validarElementos(matriz2))
        {
            for(int fila = 0; fila < RENG; fila++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    Double suma = Double.parseDouble(datos[fila][columna].toString()) +
                    Double.parseDouble(matriz2.getElemento(fila, columna).toString());
                    datos[fila][columna] = suma;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Resta todos los elementos contenidos en la matriz con los
     * valores contenidos en la segunda matriz pasada como argumento.
     * Para que dos matrices puedan restarse sus dimensiones tienen que
     * ser iguales.
     * @param matriz2 Valores a sumarse.
     * @return Regresa <b>true</b> en caso de haber podido realizar la resta,
     * o <b>false</b> en caso contrario.
     */
    public boolean restarMatriz(Matriz matriz2)
    {
        if(super.validarDim(matriz2) && validarElementos(matriz2))
        {
            for(int fila = 0; fila < RENG; fila++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    Double resta = Double.parseDouble(datos[fila][columna].toString()) -
                    Double.parseDouble(matriz2.getElemento(fila, columna).toString());
                    datos[fila][columna] = resta;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Eleva cada uno de los elementos de la matriz a la
     * potencia dada como parámetro.
     * @param escalar Exponente a elevar los valores de la matriz.
     */
    public void aplicarPotencia(double escalar)
    {
        for(int fila = 0; fila < RENG; fila++)
        {
            for(int columna = 0; columna < COL; columna++)
            {
                datos[fila][columna] = Math.pow(
                        Double.parseDouble(datos[fila][columna].toString()), escalar);
            }
        }
    }

    /**
     * Cambia los elementos contenidos en la matriz por su equivalente
     * en el logaritmo indicado como parámetro. Todos los valores de
     * la matriz deben ser diferentes de cero para poder realizar el
     * procedimiento.
     * @param tipoLog Tipo de logaritmo a convertir los valores de la
     *                matriz.
     * @return Regresa <b>true</b> en caso de haber realizado la operación
     * con éxito, o <b>false</b> en caso contrario.
     */
    public boolean aplicarLog(TipoLogaritmo tipoLog)
    {
        for(int fila = 0; fila < RENG; fila++)
        {
            Double invalido = 0.0;
            for(int columna = 0; columna < COL; columna++)
            {
                if(datos[fila][columna].equals(invalido))
                {
                    return false;
                }
            }
        }

        for(int fila = 0; fila < RENG; fila++)
        {
            for(int columna = 0; columna < COL; columna++)
            {
                if(tipoLog == TipoLogaritmo.NATURAL)
                {
                    datos[fila][columna] = Math.log((Double) datos[fila][columna]);
                }
                else
                {
                    datos[fila][columna] = Math.log10((Double) datos[fila][columna]);
                }
            }
        }
        return true;
    }

    /**
     * Sobreescribe los elementos situados en la diagonal
     * principal de la matriz con el valor dado como parámetro.
     * Los elementos no pertenecientes a la diagonal principal
     * son igualados a 0.
     * Para que el procedimiento pueda realizarse, la matriz
     * debe ser cuadrada.
     * @param valor Elemento a ser asignado en la diagonal
     *              principal de la matriz.
     */
    public void matrizDiagonal(Object valor)
    {
        if(RENG == COL && valor instanceof Number)
        {
            Double elemento = Double.parseDouble(valor.toString()) ;
            for(int fila = 0; fila < RENG; fila++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    if(fila == columna)
                    {
                        datos[fila][columna] = elemento;
                    }
                    else
                    {
                        datos[fila][columna] = 0.0;
                    }
                }
            }
        }
    }

    /**
     * Valida si la matriz es triangular superior.
     * Esto es, que tenga solamente ceros en las posiciones
     * por encima de la diagonal principal.
     * @return Regresa <b>true</b> en caso de sí ser triangular
     * superior, o <b>false</b> en caso contrario.
     */
    public boolean esDiagonalSup()
    {
        if(RENG == COL)
        {
            for(int fila = 0; fila < RENG; fila++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    if(fila < columna)
                    {
                        if(!datos[fila][columna].equals(0.0))
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Valida si la matriz es triangular inferior.
     * Esto es, que tenga solamente ceros en las posiciones
     * por debajo de la diagonal principal.
     * @return Regresa <b>true</b> en caso de sí ser triangular
     * inferior, o <b>false</b> en caso contrario.
     */
    public boolean esDiagonalInf()
    {
        if(RENG == COL)
        {
            for(int fila = 0; fila < RENG; fila++)
            {
                for(int columna = 0; columna < COL; columna++)
                {
                    if(fila > columna)
                    {
                        if(!datos[fila][columna].equals(0.0))
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public VectorNum copiarRenglon(int numRenglon)
    {
        if(numRenglon >= 0 && numRenglon < RENG)
        {// El índice del renglón es válido.

            VectorNum renglonMatriz = new VectorNum(COL);

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

    @Override
    public MatrizNum copiar()
    {
        MatrizNum matrizCopia = new MatrizNum(RENG, COL);

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
     * Convierte la matriz a un arreglo.
     * @param tipoConversion Criterio de conversión: ya sea
     *                       en base a las columnas, o las
     *                       filas.
     * @return Regresa un Arreglo con los datos de la matriz.
     */
    @Override
    public VectorNum reDim(ConversionMatriz tipoConversion)
    {
        VectorNum arreglo = new VectorNum(COL * RENG);

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
    private void reDimPorRenglon(int reng, int col, VectorNum arreglo)
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
    private void reDimPorColumna(int reng, int col, VectorNum arreglo)
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

    @Override
    public VectorNum extraerDiagonalPrincipal()
    {
        VectorNum diagonal = new VectorNum(RENG);

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
