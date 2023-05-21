package catalogos;

/**
 *
 */
public class Sucursal
{
    protected String nombre;
    protected String calle;
    protected int num;


    public Sucursal(String nombre, String calle, int num)
    {
        this.nombre = nombre;
        this.calle = calle;
        this.num = num;
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
