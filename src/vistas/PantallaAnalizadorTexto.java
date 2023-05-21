package vistas;


import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;
import utilidades.CaracterLinea;
import utilidades.TipoTexto;

public class PantallaAnalizadorTexto
{

    /**
     * Solicita el tipo de texto que se va a ingresar.
     * @return Regresa el tipo de texto indicado.
     * Puede ser de una sola línea o un archivo.
     */
    public static TipoTexto pedirTipoTexto()
    {
        TipoTexto tipo;
        SalidaEstandar.consola("\nIndica el tipo de archivo que será analizado");
        SalidaEstandar.consola("\n[1]---> Cadena de texto.");
        SalidaEstandar.consola("\n[2]---> Archivo.");
        SalidaEstandar.consola("\n---> ");
        int eleccion = Integer.parseInt(EntradaEstandar.consolaCadena());

        if(eleccion == 1)
        {
            return TipoTexto.CADENA_TEXTO;
        }
        else if(eleccion == 2)
        {
            return TipoTexto.ARCHIVO;
        }
        else
        {
            return null;
        }
    }

    /**
     * Pide los datos correspondientes de si se va a leer una cadena o un archivo
     * @param tipo Tipo de texto a leer (archivo, o cadena).
     * @return Regresa la cadena que se ingreso o el nombre del archivo que se dio.
     */
    public static String perdirDatos(TipoTexto tipo)
    {
        String nomArchivo = null;

        if(tipo == TipoTexto.CADENA_TEXTO)
        {
            SalidaEstandar.consola("\nIndica la expresión a analizar: ");
            nomArchivo = EntradaEstandar.consolaCadena();
        }
        else if(tipo == TipoTexto.ARCHIVO)
        {
            SalidaEstandar.consola("\nIndica el nombre del archivo a analizar: ");
            nomArchivo = EntradaEstandar.consolaCadena();
        }
        return nomArchivo;
    }

    /**
     * En caso de encontrar un error imprime donde esta
     * @param error Objeto con las posiciones del error.
     * @param expresionOrg Linea completa donde esta el error.
     */
    public static void imprimirError(CaracterLinea error, String expresionOrg)
    {
        SalidaEstandar.consola("\nError:\n");
        SalidaEstandar.consola(expresionOrg);
        SalidaEstandar.consola("\n");
        for(int posToken = 0; posToken < error.getCaracter() - 1; posToken++)
        {
            SalidaEstandar.consola(" ");
        }
        SalidaEstandar.consola("^");
        SalidaEstandar.consola("\nLinea: " + (error.getLinea()) ) ;
        SalidaEstandar.consola("\nCaracter: " + (error.getCaracter()) );
    }

    /**
     * Imprime si una expresión estuvo correctamente escrita.
     */
    public static void imprimirBienEscrita()
    {
        SalidaEstandar.consola("\nLa expresión ingresada está bien escrita.");
    }
}
