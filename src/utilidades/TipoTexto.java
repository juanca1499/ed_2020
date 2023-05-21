package utilidades;

public enum TipoTexto
{

    CADENA_TEXTO(1, "Cadena"),
    ARCHIVO(1, "Archivo");

    private int id;
    private String nombre;


    private TipoTexto(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
