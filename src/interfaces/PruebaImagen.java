package interfaces;

import imagen.ArchivoImagen;
import utilidades.CriterioRedimension;

public class PruebaImagen
{
    public static void main(String[] args)
    {
        ArchivoImagen imagen = new ArchivoImagen("C:\\Users\\karlo\\Desktop\\unnamed.jpg");
        imagen.leerImagen();
        imagen.trasponer();
        imagen.escribirImagen();
    }
}
