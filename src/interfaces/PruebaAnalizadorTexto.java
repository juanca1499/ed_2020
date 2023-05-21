package interfaces;

import utilidades.AnalizadorTexto;
import utilidades.TipoTexto;
import vistas.PantallaAnalizadorTexto;

public class PruebaAnalizadorTexto
{
    public static void main(String[] args)
    {
        TipoTexto tipo = PantallaAnalizadorTexto.pedirTipoTexto();
        String cadena = PantallaAnalizadorTexto.perdirDatos(tipo);
        AnalizadorTexto.analizarTexto(cadena);
    }
}
