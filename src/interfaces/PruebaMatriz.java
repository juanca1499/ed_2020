package interfaces;

import edlineal.Arreglo;
import edlineal.Matriz;
import edlineal.Matriz3D;
import entradasalida.SalidaEstandar;

public class PruebaMatriz
{
    public static void main(String[] args)
    {
        Matriz3D matriz = new Matriz3D(3,3,2);
        matriz.inicializar(0);
        matriz.imprimir();
        SalidaEstandar.consola("\n");

        matriz.setElemento(1,1,0,10);
        matriz.setElemento(0, 2, 1, 9);
        matriz.imprimir();;
        SalidaEstandar.consola("\n");

        SalidaEstandar.consola("Elemento: 1,1,0: " +
                                matriz.getElemento(1,1,0)+ "\n");

        SalidaEstandar.consola("Elemento: 100, 1, 0: " +
                                matriz.getElemento(100,1,0)+ "\n");

        Arreglo matricesObtenida = matriz.aMatriz();
        SalidaEstandar.consola("\n");

        for(int numMatriz = 0; numMatriz < matricesObtenida.getLongitud(); numMatriz++)
        {
            Matriz matrizRebanada = (Matriz)matricesObtenida.getElemento(numMatriz);
            matrizRebanada.imprimir();
            SalidaEstandar.consola("\n");
        }
    }
}






















































