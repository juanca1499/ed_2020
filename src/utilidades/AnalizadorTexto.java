package utilidades;

import com.sun.org.apache.bcel.internal.classfile.Deprecated;
import edlineal.Arreglo;
import edlineal.Pila;
import entradasalida.SalidaEstandar;
import entradasalida.archivos.LeerArchivo;
import vistas.PantallaAnalizadorTexto;


public class AnalizadorTexto
{

    /**
     * Comprueba si una cadena de caracteres para ver si esta bien escrita.
     * @param cadena Cadena a analizar.
     */
    public static void analizarTexto(String cadena)
    {
        Pila pilaCaracteres = new Pila(cadena.length());

        for(int posToken = 0; posToken < cadena.length(); posToken++)
        {
            char token = cadena.charAt(posToken);

            if(expresionApertura(String.valueOf(token)))
            {
                pilaCaracteres.push(new CaracterLinea(1, (posToken + 1), String.valueOf(token)));
            }
            else if(expresionCierre(String.valueOf(token)))
            {
                CaracterLinea apertura = (CaracterLinea) pilaCaracteres.verTope();

                if(apertura != null)
                {
                    if(!caracteresAperturaCierre(apertura.getExpresion(), String.valueOf(token)))
                    {
                        PantallaAnalizadorTexto.imprimirError((CaracterLinea) pilaCaracteres.pop(), cadena);
                        pilaCaracteres.pop();
                    }
                    else if(caracteresAperturaCierre(apertura.getExpresion() ,String.valueOf(token)))
                    {
                        pilaCaracteres.pop();
                    }
                }
                else
                {
                    PantallaAnalizadorTexto.imprimirError(new CaracterLinea(1, (posToken + 1) , String.valueOf(token)) ,cadena);
                }
            }
        }
        if(!pilaCaracteres.vacia())
        {
            while (pilaCaracteres.verTope() != null)
            {
                PantallaAnalizadorTexto.imprimirError((CaracterLinea) pilaCaracteres.pop(), cadena);
            }
        }
        else
        {
            PantallaAnalizadorTexto.imprimirBienEscrita();
        }
    }

    /**
     * Comprueba si un arreglo de cadenas de texto esta
     * bien escrita
     * @param datos Arreglo de lineas.
     */
    public static void analizarTexto(Arreglo datos)
    {
        Pila pilaCaracteres = new Pila(datos.getLongitud());

        for(int posLinea = 0; posLinea < datos.getLongitud(); posLinea++)
        {
            String linea = (String) datos.getElemento(posLinea);

            for(int posToken = 0; posToken < linea.length(); posToken++)
            {
                char token = linea.charAt(posToken);

                if (expresionApertura(String.valueOf(token)))
                {
                    pilaCaracteres.push(new CaracterLinea(1, (posToken + 1), String.valueOf(token)));
                }
                else if (expresionCierre(String.valueOf(token)))
                {
                    CaracterLinea apertura = (CaracterLinea) pilaCaracteres.verTope();

                    if (apertura != null)
                    {
                        if (!caracteresAperturaCierre(apertura.getExpresion(), String.valueOf(token)))
                        {
                            PantallaAnalizadorTexto.imprimirError((CaracterLinea) pilaCaracteres.pop(), linea);
                            pilaCaracteres.pop();
                        }
                        else if (caracteresAperturaCierre(apertura.getExpresion(), String.valueOf(token)))
                        {
                            pilaCaracteres.pop();
                        }
                    }
                    else
                    {
                        PantallaAnalizadorTexto.imprimirError(new CaracterLinea(1, (posToken + 1), String.valueOf(token)), linea);
                    }
                }
            }
        }
    }

    /**
     * Evalua si el token dado es una expresión de apertura
     * @param token Token a ser analizado.
     * @return Regresa true en caso positivo, o false en caso contrario.
     */
    private static boolean expresionApertura(String token)
    {
        if(token.equals("(") || token.equals("[") || token.equals("{") || token.equals("/**"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Evalua si las expresiones coinciden a una apertura y a un cierre.
     * @param expresionApertura Expresion de apertura.
     * @param expresionCierre Expresion de cierre.
     * @return Regresa true en caso positivo, o false en caso contrario.
     */
    private static boolean caracteresAperturaCierre(String expresionApertura, String expresionCierre)
    {
        if(expresionApertura.equals("(") && expresionCierre.equals(")"))
        {
            return true;
        }
        else if(expresionApertura.equals("[") && expresionCierre.equals("]"))
        {
            return true;
        }
        else if(expresionApertura.equals("{") && expresionCierre.equals("}"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Comprueba si el token dado es una expresión de cierre.
     * @param token Expresión a ser comprobada.
     * @return Regresa true en caso positivo, o false en caso contrario.
     */
    private static boolean expresionCierre(String token)
    {
        if(token.equals(")") || token.equals("]") || token.equals("}") || token.equals("*/"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
