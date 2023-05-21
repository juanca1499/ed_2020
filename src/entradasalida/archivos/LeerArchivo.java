package entradasalida.archivos;

import edlineal.Arreglo;
import edlineal.ListaEnlazadaIndexada;
import entradasalida.SalidaEstandar;

import java.io.*;

/**
 * Clase que permite manipular lectura de archivos.
 * @author Calse ED.
 * @version 1.0
 */
public class LeerArchivo {

    /**
     * Método que permite guardar las líneas de un archivo de texto en un arreglo.
     * Una línea por cada posición del arreglo.
     * @param archivo Es el archivo con ruta para leer el archivo.
     * @param tamanioArchivo Es el número de líneas que tiene el archivo.
     * @return Regresa un TDA Arreglo con todas las líneas leidas del archivo.
     * Una línea por cada posición del arreglo.
     */
    public static Arreglo leer(String archivo, int tamanioArchivo){
        FileReader input=null;

        Arreglo datos=new Arreglo(tamanioArchivo);
        try {
            String cadena=null;
            input = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(input);
            while((cadena = buffer.readLine())!=null) {
                datos.agregar(cadena);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                input.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return datos;
    }

    /**
     * Método que permite guardar las líneas de un archivo de texto en una ListaIndexada.
     * @param archivo Es el archivo con ruta para leer el archivo.
     * @return Regresa un TDA ListaEnlazadaIndexada con todas las líneas leidas del archivo.
     * Una línea por cada posición de la Lista.
     */
    public static ListaEnlazadaIndexada leer(String archivo)
    {
        int index = 0;
        FileReader input = null;
        ListaEnlazadaIndexada lista = new ListaEnlazadaIndexada();

        try {
            String cadena=null;
            input = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(input);
            while((cadena = buffer.readLine())!=null) {
                lista.insertar(index,cadena);
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                input.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return lista;
    }

    /**
     * Método recursivo que permite guardar las líneas de un archivo de texto en una ListaIndexada.
     * @param lista Lista donde se guardarán los datos.
     * @param buffer Lector del archivo.
     * @return Regresa un TDA ListaEnlazadaIndexada con todas las líneas leidas del archivo.
     * Una línea por cada posición de la Lista.
     */
    public static ListaEnlazadaIndexada leer(ListaEnlazadaIndexada lista, BufferedReader buffer)
    {
        return null;
    }



}
