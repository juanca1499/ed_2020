package interfaces;

import audio.AudioFile;

public class PruebaAudio
{
    public static void main(String[] args)
    {
        AudioFile audio = new AudioFile("C:\\Users\\karlo\\Desktop\\Laboratorio de Estructura de Datos\\ed_2020\\src\\audio\\Silencio.wav");
        audio.leerAudio();
        audio.eliminarSilencio();
        audio.escribirAudio();
    }
}
