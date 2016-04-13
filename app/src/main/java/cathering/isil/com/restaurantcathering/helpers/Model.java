package cathering.isil.com.restaurantcathering.helpers;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import cathering.isil.com.restaurantcathering.datos.AppPreferences;
import cathering.isil.com.restaurantcathering.datos.Datos;
import cathering.isil.com.restaurantcathering.objects.Local;
import cathering.isil.com.restaurantcathering.objects.Pedido;
import cathering.isil.com.restaurantcathering.objects.Plato;
import cathering.isil.com.restaurantcathering.objects.Trago;

/**
 * Created by helbert on 20/05/15.
 */
public class Model {

    private Context context;
    private Datos data;
    private Intent intent;
    private AppPreferences preferences;

    public Local local;
    public ArrayList<Plato> platos;
    public ArrayList<Trago> tragos;
    public ArrayList<Pedido> pedidos;
    public ArrayList<String> ids;
    public ArrayList<String> cantidad;

    public Model(Context context){
        this.context=context;
        data = new Datos(context);
        preferences = new AppPreferences(context);
    }


    public void loadLocal(){
        local = (Local) data.listarDataLocal();
    }

    public void loadPlatos(String tipo){
        platos = data.listarCartaPlatos(tipo);
    }

    public void registerPlatos(int id, String nombre, String descripcion, String precio, String imagen, String tipo){
        data.registerPlatosLocal(id,nombre,descripcion,precio,imagen,tipo);
    }

    public void removePedido(int id){
        data.removePedido(id);
    }

    public void registerPedido(int id, String nombre, String tipo, int cantidad, String monto, int status){
        data.registerPedidos(id, nombre, tipo, cantidad, monto, status);
    }

    public int cantidadPedido(int id,String tipo){
        return data.getCantServiciosByPedidos(id,tipo);
    }

    public void cleanPedido(){
        data.cleanPedidos();
    }

    public int totalPedido(){
        return data.totalPedidos();
    }

    public void listarPedido(){
        pedidos = data.listarPedidos();
    }


}
