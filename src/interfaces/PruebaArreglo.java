package interfaces;

import edlineal.Arreglo;
import edlineal.VectorNum;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;
import sun.text.resources.no.CollationData_no;

import javax.swing.plaf.SliderUI;
import java.util.List;

/**
 *
 * @author Clase de estructura de datos.
 */
public class PruebaArreglo
{
    public static void main(String[] args)
    {
        Arreglo arreglo = new Arreglo(5);


        //Pruebas Practica Arreglos

        arreglo.agregar(45); //0
        arreglo.agregar(5);  //1
        arreglo.agregar(20); //2
        arreglo.agregar(9); //3
        arreglo.agregar(7); //4


        VectorNum vector = new VectorNum(5);
        vector.agregar(10);
        vector.agregar(25);
        vector.agregar(13);
        vector.agregar(11);
        vector.agregar(19);

        VectorNum vectorNum = new VectorNum(5);
        vectorNum.agregar(10);
        vectorNum.agregar(30);
        vectorNum.agregar(17);
        vectorNum.agregar(13);
        vectorNum.agregar(24);

        vector.setElemento(1, "11");
        vector.imprimir();

        SalidaEstandar.consola("\n");
        vector.multEscalar(10);
        vector.imprimir();

        SalidaEstandar.consola("\n");
        vector.sumaEscalar(10);
        vector.imprimir();

        SalidaEstandar.consola("\n");
        vector.sumar(vectorNum);
        vector.imprimir();

        SalidaEstandar.consola("\n");
        vector.multiplicar(vectorNum);
        vector.imprimir();

        vector = new VectorNum(5);
        vector.agregar(10);
        vector.agregar(25);
        vector.agregar(13);
        vector.agregar(11);
        vector.agregar(19);

        vectorNum = new VectorNum(5);
        vectorNum.agregar(10);
        vectorNum.agregar(30);
        vectorNum.agregar(17);
        vectorNum.agregar(13);
        vectorNum.agregar(24);

        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("Producto punto= "+vector.productoPunto(vectorNum));
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("Magnitud= "+vector.magnitud());
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("Norma euc= "+vector.normaEuclidiana(vectorNum));

    }

}
