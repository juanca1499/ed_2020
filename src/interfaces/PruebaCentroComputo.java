package interfaces;

import catalogos.Aplicacion;
import catalogos.CentroComputo;
import catalogos.Computadora;
import entradasalida.SalidaEstandar;
import utilidades.TipoImpresion;

/**
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaCentroComputo
{
   public static void main(String[] args)
   {
       CentroComputo centroComputo = new CentroComputo(30);
       Computadora comp1 = new Computadora(1, 2, "Kingston SSD 128 GB",
               "Intel Core i5 5th Gen", "Dell");
       Computadora comp2 = new Computadora(2, 32, "Adata HDD 2 TB",
               "AMD Ryzen 3", "Asus");
       Computadora comp3 = new Computadora(3, 16, "Samsung SSD 2 TB",
               "Intel Core i9 10th Gen", "Hp");
       Computadora comp4 = new Computadora(4, 64, "Samsung SSD 8 TB",
               "Intel Core i9 10th Gen", "Alienware");

       Aplicacion app1 = new Aplicacion("Calculadora", "aqui.jpg", "4.31F", 2);
       app1.agregarAutor("Juan");
       app1.agregarAutor("Carlos");
       Aplicacion app2 = new Aplicacion("Firefox", "firefox.png", "10.2.3.5", 20);
       app2.agregarAutor("George");
       Aplicacion app3 = new Aplicacion("WhatsApp", "Whats.png", "5.2.9.3", 10);
       app3.agregarAutor("García");
       app3.agregarAutor("Murillo");
       app3.agregarAutor("Programador Pro");

       SalidaEstandar.consola("\nOPERACIONES DEL CENTRO DE CÓMPUTO");

       SalidaEstandar.consola("\n\nf) Agregar Computadoras: ");
       SalidaEstandar.consola("\nAgregando Computadora1: " + centroComputo.agregarComputadora(comp1));
       SalidaEstandar.consola("\nAgregando Computadora2: " + centroComputo.agregarComputadora(comp2));
       SalidaEstandar.consola("\nAgregando Computadora3: " + centroComputo.agregarComputadora(comp3));
       SalidaEstandar.consola("\nAgregando Computadora4: " + centroComputo.agregarComputadora(comp4));

       SalidaEstandar.consola("\n\nd) Agregar aplicaciones a las Computadoras: ");
       SalidaEstandar.consola("\nAgregando app a: " + comp1 + ": " +
               centroComputo.agregarAplicacion(comp1.getNumero(), app1));
                centroComputo.agregarAplicacion(comp1.getNumero(), app3);
       SalidaEstandar.consola("\nAgregando app a: " + comp2 + ": " +
               centroComputo.agregarAplicacion(comp2.getNumero(), app2));
       SalidaEstandar.consola("\nAgregando app a: " + comp3 + ": " +
               centroComputo.agregarAplicacion(comp3.getNumero(), app3));


       SalidaEstandar.consola("\n\na) Imprimir todas las Computadoras con sus datos: ");
            centroComputo.imprimirDatosTodasComputadoras();

       SalidaEstandar.consola("\n\nc) Imprimir carecterísticas detalladas de la Computadora: " + comp1);
            centroComputo.imprimirDatosComputadora(comp1.getNumero(), TipoImpresion.DETALLADA);

       SalidaEstandar.consola("\n\nb) Computadoras con RAM de 16 GB:");
            centroComputo.imprimirComputadorasConCantRam(16);

       SalidaEstandar.consola("\n\ne) Eliminando aplicación: " + app3 + " de: " + comp1 + ": " +
            centroComputo.eliminarAplicacion(comp1.getNumero(), app3.getNombre()));

       SalidaEstandar.consola("\n\ng) Dando de baja la computadora: " + comp4 + ": " +
            centroComputo.eliminarComputadora(comp4.getNumero()));

       SalidaEstandar.consola("\n\ng) Computadoras que tienen instalado Firefox:");
       centroComputo.imprimirComputadorasConApp(app2);

       SalidaEstandar.consola("\n\ni) Computadoras que pueden ejecutar: " + app2 + ": ");
       centroComputo.imprimirComputadorasCompatibles(app2);

       SalidaEstandar.consola("\n\nj) Computadoras que tengan aplicaciones que necesiten 16 gb o más de RAM:");
       centroComputo.imprimirComputadorasAppsRam(16);


   }
}
