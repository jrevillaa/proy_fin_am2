package cathering.isil.com.restaurantcathering.objects;

/**
 * Created by helbert on 21/06/15.
 */
public class Pedido {

    private int id;
    private String nombre;
    private String tipo;
    private int cantidad;
    private String monto;
    private int status; // 0 sin atender, 1 atendido


    public Pedido(int id,String nombre , String tipo, int cantidad, String monto, int status){
        this.id=id;
        this.nombre=nombre;
        this.tipo=tipo;
        this.cantidad=cantidad;
        this.monto=monto;
        this.status=status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
