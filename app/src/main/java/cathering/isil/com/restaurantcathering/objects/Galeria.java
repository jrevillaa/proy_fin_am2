package cathering.isil.com.restaurantcathering.objects;

/**
 * Created by helbert on 01/06/15.
 */
public class Galeria {

    int id;
    String nombre;
    String descripcion;
    String imagen;

    public Galeria(int id, String nombre, String descripcion, String imagen){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imagen=imagen;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
