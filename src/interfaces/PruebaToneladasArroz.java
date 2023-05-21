package interfaces;


import edlineal.VectorNum;
import entradasalida.SalidaEstandar;
import matematicas.Estadistica;
import utilidades.CriterioOrden;

/**
 * @author Clase de estructura de datos.
 */
public class PruebaToneladasArroz
{
    public static void main(String[] args)
    {
        VectorNum arrozYear1 = new VectorNum(12);
        VectorNum arrozYear2 = new VectorNum(12);
        VectorNum arrozYear3 = new VectorNum(12);
        VectorNum arrozYear4 = new VectorNum(12);
        VectorNum arrozYear5 = new VectorNum(12);

        arrozYear1.agregar(10);
        arrozYear1.agregar(15); 
        arrozYear1.agregar(20);
        arrozYear1.agregar(5);
        arrozYear1.agregar(3);
        arrozYear1.agregar(8);
        arrozYear1.agregar(30);
        arrozYear1.agregar(50);
        arrozYear1.agregar(100);
        arrozYear1.agregar(0);
        arrozYear1.agregar(7);
        arrozYear1.agregar(3);


        SalidaEstandar.consola("Estadisticas sobre el primer año:");
        SalidaEstandar.consola("\nPromedio anual de toneladas= " + Estadistica.promedio(arrozYear1));
        SalidaEstandar.consola("\nCantidad de meses con una produccion mayor al promedio anual= "+
                                        Estadistica.mayoresDelPromedio(arrozYear1));
        SalidaEstandar.consola("\nMes con la menor prododucción anual= "+arrozYear1.getMenor(true));
        SalidaEstandar.consola("\nToneladas producidas en el primer mes del año= " +
                                        arrozYear1.getElemento(0));
        SalidaEstandar.consola("\nToneladas producidas en el primer trimestre del año= "+
                                        Estadistica.suma(arrozYear1, 3));

        arrozYear2.agregar(10);
        arrozYear2.agregar(30);
        arrozYear2.rellenar(3,3);
        arrozYear2.agregar(15);
        arrozYear2.rellenar(7,4);
        arrozYear2.agregar(1);
        arrozYear2.agregar(0);
        SalidaEstandar.consola("\n\n\nEstadisticas sobre el segundo año:");
        SalidaEstandar.consola("\nPromedio anual de toneladas= " + Estadistica.promedio(arrozYear2));
        SalidaEstandar.consola("\nCantidad de meses con una produccion mayor al promedio anual= "+
                Estadistica.mayoresDelPromedio(arrozYear2));
        SalidaEstandar.consola("\nMes con la menor prododucción anual= "+arrozYear2.getMenor(true));
        SalidaEstandar.consola("\nToneladas producidas en el primer mes del año= " +
                arrozYear2.getElemento(0));
        SalidaEstandar.consola("\nToneladas producidas en el primer trimestre del año= "+
                Estadistica.suma(arrozYear2, 3));


        arrozYear3.rellenar(2,3);
        arrozYear3.agregar(5);
        arrozYear3.agregar(50);
        arrozYear3.agregar(7);
        arrozYear3.rellenar(10,4);
        arrozYear3.agregar(15);
        arrozYear3.agregar(20);
        SalidaEstandar.consola("\n\n\nEstadisticas sobre el tercer año:");
        SalidaEstandar.consola("\nPromedio anual de toneladas= " + Estadistica.promedio(arrozYear3));
        SalidaEstandar.consola("\nCantidad de meses con una produccion mayor al promedio anual= "+
                Estadistica.mayoresDelPromedio(arrozYear3));
        SalidaEstandar.consola("\nMes con la menor prododucción anual= "+arrozYear3.getMenor(true));
        SalidaEstandar.consola("\nToneladas producidas en el primer mes del año= " +
                arrozYear3.getElemento(0));
        SalidaEstandar.consola("\nToneladas producidas en el primer trimestre del año= "+
                Estadistica.suma(arrozYear3, 3));


        arrozYear4.rellenar(5,5);
        arrozYear4.agregar(30);
        arrozYear4.agregar(15);
        arrozYear4.agregar(1);
        arrozYear4.agregar(2);
        arrozYear4.agregar(7);
        arrozYear4.agregar(0);
        arrozYear4.agregar(55);
        SalidaEstandar.consola("\n\n\nEstadisticas sobre el cuarto año:");
        SalidaEstandar.consola("\nPromedio anual de toneladas= " + Estadistica.promedio(arrozYear4));
        SalidaEstandar.consola("\nCantidad de meses con una produccion mayor al promedio anual= "+
                Estadistica.mayoresDelPromedio(arrozYear4));
        SalidaEstandar.consola("\nMes con la menor prododucción anual= "+arrozYear4.getMenor(true));
        SalidaEstandar.consola("\nToneladas producidas en el primer mes del año= " +
                arrozYear4.getElemento(0));
        SalidaEstandar.consola("\nToneladas producidas en el primer trimestre del año= "+
                Estadistica.suma(arrozYear4, 3));

        arrozYear5.agregar(10);
        arrozYear5.agregar(11);
        arrozYear5.agregar(9);
        arrozYear5.agregar(13);
        arrozYear5.agregar(18);
        arrozYear5.agregar(20);
        arrozYear5.rellenar(7,6);
        SalidaEstandar.consola("\n\n\nEstadisticas sobre el quinto año:");
        SalidaEstandar.consola("\nPromedio anual de toneladas= " + Estadistica.promedio(arrozYear5));
        SalidaEstandar.consola("\nCantidad de meses con una produccion mayor al promedio anual= "+
                Estadistica.mayoresDelPromedio(arrozYear5));
        SalidaEstandar.consola("\nMes con la menor prododucción anual= "+arrozYear5.getMenor(true));
        SalidaEstandar.consola("\nToneladas producidas en el primer mes del año= " +
                arrozYear5.getElemento(0));
        SalidaEstandar.consola("\nToneladas producidas en el primer trimestre del año= "+
                Estadistica.suma(arrozYear5, 3));


        SalidaEstandar.consola("Enumerado: "+ CriterioOrden.ASCENDENTE);

    }
}
