package cathering.isil.com.restaurantcathering.objects;

/**
 * Created by helbert on 20/05/15.
 */
public class Local {

    int id_codigo;
    String nombre;
    String ubicacion;
    String horario;
    String email;
    String telefono;

    public Local(int id_codigo, String nombre, String ubicacion, String horario, String email, String telefono){
        this.id_codigo=id_codigo;
        this.nombre=nombre;
        this.ubicacion=ubicacion;
        this.horario=horario;
        this.email=email;
        this.telefono=telefono;
    }

    public int getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(int id_codigo) {
        this.id_codigo = id_codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
