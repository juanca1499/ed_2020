package audio;

import java.io.*;

import audio.wavfile.*;
import edlineal.VectorNum;
import utilidades.Casteador;


public class AudioFile {
    private long numFrames;  //numero de tramas, número de muestras totales del archivo por canal
    private long sampleRate; //numero de muestras por segundo a la que se discretiza la señal
    private int numChannels; //número de canales
    private double[] buffer; //audio original
    private double[] buffer2; //audio modificado
    private String archivo;   //nombre de archivo dado por el usuario
    private WavFile wavFileR; //apuntador de archivo leido
    private WavFile wavFileW;  //apuntador de archivo a escribir
    private String nomArchivo; //nombre archivo (uno.wav)
    private String nomRuta;    //ruta donde esta guardado el archivo (/home)
    private int validBits;     //bits usados para la discretización
    private VectorNum vectorAudio;   //Vector para el almacenamiento del audio modificado.


    public AudioFile(String archivo) {
        this.archivo = archivo;
        // Abre el archivo wav y asigna parámetros a las variables
        try {
            File file = new File(archivo);
            wavFileR = WavFile.openWavFile(file);
            nomArchivo = file.getName();
            nomRuta = file.getParent();
        } catch (Exception e) {

        }
        numChannels = wavFileR.getNumChannels();
        numFrames = wavFileR.getNumFrames();
        sampleRate = wavFileR.getSampleRate();
        validBits=wavFileR.getValidBits();
    }

    /**
     * Guarda el audio en un arreglo de <b>double</b>
     * para su manipulacion.
     */
    public void leerAudio() {
        try {

            // Muestra datos del archivo
            wavFileR.display();

            // Crea buffer de origen y de temporal
            buffer = new double[(int) numFrames * numChannels];
            buffer2 = new double[buffer.length];

            //tramas leidas
            int framesRead;

            // Lee tramas totales
            framesRead = wavFileR.readFrames(buffer, (int) numFrames);

            // Recorrer todas las tramas del archivo y guardarlas en el arreglo.
            for (int s = 0; s < framesRead * numChannels; s++) {
                buffer2[s] = buffer[s];
            }

            //Asignacion de buffer2 al vector
            vectorAudio = new VectorNum((int) numFrames * numChannels);
            vectorAudio.setArreglo(Casteador.convertirPrimitivo(buffer2));

            // Cierra archivo
            wavFileR.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Realiza las modificaciones indicadas al audio.
     */
    public void escribirAudio() {
        try {

            //Crear el apuntador al archivo para guardar datos del temporal
            File file = new File(nomRuta + "/2_" + nomArchivo);

            // Creamos un nuevo archivo de audio con los mismos datos que el original
            wavFileW = WavFile.newWavFile(file, numChannels, numFrames, validBits, sampleRate);

            //Se obtienen las modificaciones hechas al audio.
            buffer2 = Casteador.convertirDoublePrimitivo(vectorAudio.leerArreglo());

            // Escribimos los frames o muestras totales del temporal
            wavFileW.writeFrames(buffer2, (int) numFrames);

            // Cerramos el archivo
            wavFileW.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Aumenta el volumen del audio las veces indicadas por el parámetro.
     * @param intensidad Criterio para subir el volumen.
     */
    public void subirVolumen(int intensidad)
    {
        vectorAudio.multEscalar(intensidad);
    }

    /**
     * Reduce el volumen del audio las veces indicadas por el parámetro,
     * @param intensidad Criterio para bajar el volumen.
     */
    public void bajarVolumen(int intensidad)
    {
        vectorAudio.divEscalar(intensidad);
    }

    /**
     * Aumenta la velocidad del audio las veces indicadas por el parámetro.
     * @param intensidad Criterio de aceleración.
     */
    public void acelear(int intensidad)
    {
        if(intensidad > 1)
        {
            int nuevoTam = vectorAudio.capacidad() / intensidad;
            VectorNum otroVector = new VectorNum(nuevoTam);

            for(int contador = 0; contador < vectorAudio.getLongitud() - intensidad; contador += intensidad)
            {
                Double valor1 = (Double) vectorAudio.getElemento(contador);
                Double valor2 = (Double) vectorAudio.getElemento(contador + 1);
                Double resultado = (valor1 + valor2) / 2;
                otroVector.agregar(resultado);
            }
            vectorAudio.setArreglo(otroVector.leerArreglo());
            numFrames = vectorAudio.capacidad() / numChannels;
        }
    }

    /**
     * Reduce la velocidad del audio las veces indicadas por el parámetro.
     * @param intensidad Criterio de ralentización.
     */
    public void retrasar(int intensidad)
    {
        if(intensidad > 1)
        {
            int nuevoTam = vectorAudio.capacidad() * intensidad;
            VectorNum otroVector = new VectorNum(nuevoTam);

            for(int contador = 0; contador < vectorAudio.getLongitud() - 1; contador++)
            {
                Double valor1 = (Double) vectorAudio.getElemento(contador);
                otroVector.agregar(valor1);
                Double valor2 = (Double) vectorAudio.getElemento(contador + 1);
                Double resultado = (valor1 + valor2) / 2;

                for(int contador2 = 0; contador2 < intensidad - 1; contador2++)
                {
                    otroVector.agregar(resultado);
                }
            }
            for(int contador = 0; contador < intensidad - 1; contador++)
            {
                otroVector.agregar(0.0);
            }
            vectorAudio.setArreglo(otroVector.leerArreglo());
            numFrames = vectorAudio.capacidad() / numChannels;
        }
    }

    /**
     * Remueve del audio los espacios sin sonido.
     */
    public void eliminarSilencio()
    {
        int reduccion = 0;

        for(int contador = 0; contador < vectorAudio.getLongitud(); contador++)
        {
            if((Double)vectorAudio.getElemento(contador) == 0)
            {
                reduccion++;
            }
        }
        if(reduccion != 0)
        {
            reduccion = vectorAudio.capacidad() - reduccion;
            VectorNum otroVector = new VectorNum(reduccion);

            for(int contador = 0; contador < vectorAudio.getLongitud() - 1; contador++)
            {
                if((Double)vectorAudio.getElemento(contador) > 0 || (Double)vectorAudio.getElemento(contador) < 0)
                {
                    otroVector.agregar(vectorAudio.getElemento(contador));
                }
            }
            vectorAudio.setArreglo(otroVector.leerArreglo());
            numFrames = vectorAudio.capacidad() / numChannels;
        }
    }
}

