package imagen;

import edlineal.VectorNum;
import edlineal.Matriz;
import utilidades.CriterioRedimension;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Esta clase define el TDA ArchivoImagen.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ArchivoImagen
{
    /** Guarda la ruta en que se encuentra la imágen original. */
    private String rutaImagen;
    /** Guarda el nombre que corresponde a la imagen original. */
    private String nombreImagen;
    /** Guarda la imagen original en su representación digital. */
    private BufferedImage imgOriginal;
    /** Guarda la imagen modificada en su representación digital. */
    private BufferedImage imgModificada;
    /** Guarda la anchura de la imagen original. */
    private int anchoOrg;
    /** Guarda la altura de la imagen original. */
    private int altoOrg;
    /** Guarda la anchura de la imagen modificada. */
    private int anchoMod;
    /** Guarda la altura de la imagen modificada. */
    private int altoMod;
    /** Matriz de pixeles de la imagen. */
    private Matriz matrizImagen;

    /**
     * Define el constructor de TDA ArchivoImagen.
     * @param rutaImagen Lozalización donde se encuentra la
     *             imagen a manipular.
     */
    public ArchivoImagen(String rutaImagen)
    {
        this.rutaImagen = rutaImagen;
    }

    /**
     * Realiza la lectura de la imagen desde la ruta indicada.
     * @return Regresa <b>true</b> en caso de haber podido leer
     * la imagen exitosamente, o <b>false</b> en caso contrario.
     */
    public boolean leerImagen()
    {
        try
        {
            //Se extrae la imagen del archivo junto con sus propiedades.
            File archivo = new File(rutaImagen);
            imgOriginal = ImageIO.read(archivo);
            imgModificada = new BufferedImage(imgOriginal.getWidth(), imgOriginal.getHeight(), imgOriginal.getType());
            this.altoOrg = imgOriginal.getHeight();
            this.anchoOrg = imgOriginal.getHeight();
            this.rutaImagen = archivo.getParent();
            this.nombreImagen = archivo.getName();
            matrizImagen = getMatrizImagen(imgOriginal);
            return true;
        }
        catch (IOException eio)
        {
            eio.printStackTrace();
            return false;
        }
    }

    /**
     * Guarda en un nuevo archivo las modificaciones realizadas
     * a la imagen original.
     * @return Regresa <b>true</b> en caso de haber podido guardar
     * la imagen con éxito, o <b>false</b> en caso contrario.
     */
    public boolean escribirImagen()
    {
        try
        {
            //Se itera toda la matriz de pixeles.
            for(int renglon = 0; renglon < matrizImagen.numRenglones(); renglon++)
            {
                for(int columna = 0; columna < matrizImagen.numColumnas(); columna++)
                {
                    //Se extrae cada pixel para obtener sus propiedades.
                    VectorNum pixeles = (VectorNum) matrizImagen.getElemento(renglon, columna);
                    if(pixeles != null) {

                        int alfa = (int) pixeles.getElemento(0);
                        int rojo = (int) pixeles.getElemento(1);
                        int verde = (int) pixeles.getElemento(2);
                        int azul = (int) pixeles.getElemento(3);
                        //Se convierten sus propiedades a rgb y se agrega a la imagen.
                        Color color = new Color(rojo, verde, azul, alfa);
                        imgModificada.setRGB(renglon, columna, color.getRGB());
                    }

                }
            }
            //Se guarda la imagen modificada
            ImageIO.write(imgModificada, "JPG", new File(rutaImagen+"/MOD_"+nombreImagen));
            return true;
        }
        catch (IOException eio)
        {
            eio.printStackTrace();
            return false;
        }
    }

    /**
     * Genera una matriz de los pixeles de la imagen que es pasada
     * como parámetro.
     * @param imagen Imagen de donde se tomarán los pixeles.
     * @return Regresa la matriz con cada pixel de la imagen.
     */
    public Matriz getMatrizImagen(BufferedImage imagen)
    {
        //Se asigna la dimension a la matriz según los pixeles de alto y de ancho que tiene la imagen.
        Matriz matrizPixeles = new Matriz(imgOriginal.getHeight(), imgOriginal.getWidth());

        //Se iteran todos los pixeles de la imagen.
        for(int renglon = 0; renglon < imagen.getHeight(); renglon++)
        {
            for(int columna = 0; columna < imagen.getWidth(); columna++)
            {
                //Se crea un VectorNum de cuatro posiciones para agregar las propeidades de un pixel.
                VectorNum propiedadesPixel;
                //Se extrae el pixel de la posición y después sus propiedades.
                int pixel = imagen.getRGB(renglon, columna);
                propiedadesPixel = this.setRGB(pixel);
                //Se agrega el VectorNum (pixel) a la matriz de pixeles de la imagen.
                matrizPixeles.setElemento(renglon, columna, propiedadesPixel);
            }
        }
        return matrizPixeles;
    }

    /**
     * Realiza la transformación de la imagen a escala de grises.
     */
    public void convertirEscalaGrises()
    {
        //Se itera cada pixel de la imagen.
        for(int renglon = 0; renglon < matrizImagen.numRenglones(); renglon++)
        {
            for(int columna = 0; columna < matrizImagen.numColumnas(); columna++)
            {
                //Se obtienen los valores RGB contenidos en cada pixel.
                VectorNum pixeles = (VectorNum) matrizImagen.getElemento(renglon, columna);

                int rojo = (int)pixeles.getElemento(1);
                int verde = (int)pixeles.getElemento(2);
                int azul = (int)pixeles.getElemento(3);
                int promedio = (rojo + verde + azul) / 3;
                //Se agrega el promedio de los colores nuevamente al pixel.
                pixeles.setElemento(1, promedio);
                pixeles.setElemento(2, promedio);
                pixeles.setElemento(3, promedio);
            }
        }
    }

    /**
     * Altera el brillo de la imagen aumentándolo o bajándolo según
     * el parámetro dado.
     * @param cantidad Criterio para subir o bajar el brillo.
     */
    public void modificarBrillo(int cantidad)
    {
        //Se itera cada pixel de la imagen.

        if(cantidad != 0)
        {
            for (int renglon = 0; renglon < matrizImagen.numRenglones(); renglon++)
            {
                for (int columna = 0; columna < matrizImagen.numColumnas(); columna++)
                {
                    //Se obtienen los valores RGB contenidos en cada pixel.
                    VectorNum pixel = (VectorNum) matrizImagen.getElemento(renglon, columna);
                    int alfa = (int) pixel.getElemento(0);
                    int rojo = (int) pixel.getElemento(1);
                    int verde = (int) pixel.getElemento(2);
                    int azul = (int) pixel.getElemento(3);


                    //Se suma el brillo a cada canal.
                    alfa += cantidad;
                    rojo += cantidad;
                    verde += cantidad;
                    azul += cantidad;

                    //Se evalua que no contengan valores inválidos.
                    if(alfa < 0) { alfa = 0; }
                    if(alfa > 255) {alfa = 255; }
                    if(rojo < 0) { rojo = 0; }
                    if(rojo > 255) {rojo = 255; }
                    if(verde < 0) { verde = 0; }
                    if(verde> 255) {verde = 255; }
                    if(azul < 0) {  azul = 0; }
                    if(azul > 255) { azul = 255; }

                    pixel.setElemento(0, alfa);
                    pixel.setElemento(1, rojo);
                    pixel.setElemento(2, verde);
                    pixel.setElemento(3, azul);
                    
                    //Se agrega el pixel a la matriz.
                    matrizImagen.setElemento(renglon, columna, pixel);
                }
            }
        }
    }

    /**
     * Invierte la imagen en 180 grados en posición horizontal.
     */
    public void invertirHorizontal()
    {
        matrizImagen.invertirFilas();
    }

    /**
     * Invierte la imagen 180 grados en posición vertical.
     */
    public void invertirVertical()
    {
        matrizImagen.invertirColumnas();
    }

    /**
     * Traspone la imagen, girándola 90 grados a la izquierda.
     */
    public void trasponer()
    {
        matrizImagen.aplicarTraspuesta();
    }


    /**
     * Cambia el tamaño de la imagen según el criterio dado y las veces
     * que se indiquen.
     * @param criterio Indica si la imagen será aumentada o reducida.
     * @param veces Define las veces que la imagen será aumentada o reducida.
     */
    public void redimensionar(CriterioRedimension criterio, int veces)
    {

        if(criterio == CriterioRedimension.AUMENTAR)
        {
            altoMod = altoOrg * veces;
            anchoMod = anchoOrg * veces;
            Matriz nuevaMatriz = new Matriz(altoMod, anchoMod);
            imgModificada = new BufferedImage(altoMod, anchoMod ,imgOriginal.getType());

            for(int renglon = 0; renglon < nuevaMatriz.numRenglones(); renglon ++)
            {
                for(int columna = 0; columna < nuevaMatriz.numColumnas() ; columna ++)
                {
                    VectorNum pixel = (VectorNum) matrizImagen.getElemento(renglon/veces, columna/veces);

                    for(int renglon2 = renglon; renglon2 < (veces + renglon); renglon2++)
                    {
                        for(int columna2 = columna; columna2 < (veces + columna); columna2++)
                        {
                            nuevaMatriz.setElemento(renglon2, columna2, pixel);
                        }
                    }
                }
            }
            matrizImagen.definirMatriz(nuevaMatriz);
        }
        else if (criterio == CriterioRedimension.REDUCIR)
        {
            altoMod = altoOrg / veces;
            anchoMod = anchoOrg / veces;
            Matriz nuevaMatriz = new Matriz(altoMod, anchoMod);
            imgModificada = new BufferedImage(altoMod, anchoMod ,imgOriginal.getType());

            for(int renglon = 0; renglon < matrizImagen.numRenglones(); renglon++)
            {
                for(int columna = 0; columna < matrizImagen.numColumnas(); columna++)
                {
                    VectorNum pixel = (VectorNum) matrizImagen.getElemento(renglon*veces, columna*veces);
                    nuevaMatriz.setElemento(renglon, columna, pixel);
                }
            }
            matrizImagen.definirMatriz(nuevaMatriz);
        }
    }

    /**
     * Convierte el pixel dado como parámetro a un valor RGB
     * @param pixel Pixel a ser convertido.
     * @return Regresa el pixel convertido a RGB.
     */
    private int getRgb(VectorNum pixel)
    {
        int alfa = (int)pixel.getElemento(0);
        int rojo = (int)pixel.getElemento(1);
        int verde = (int)pixel.getElemento(2);
        int azul = (int)pixel.getElemento(3);
        int rgb = (alfa << 32) | (rojo << 16) | (verde << 8) | azul;
        return rgb;
    }

    /**
     * Asigna el valor de RGB a un pixel.
     * @param rgb RBG a ser convertido a pixel.
     * @return Regresa el VectorNum (pixel) con el RGB
     * asignado.
     */
    private VectorNum setRGB(int rgb)
    {
        VectorNum VectorNum = new VectorNum(4);
        int alfa = rgb >> 32 & 0xff;
        int rojo = rgb >> 16 & 0xff;
        int verde = rgb >> 8 & 0xff;
        int azul = rgb & 0xff;

        VectorNum.agregar(alfa);
        VectorNum.agregar(rojo);
        VectorNum.agregar(verde);
        VectorNum.agregar(azul);
        return VectorNum;
    }
}
