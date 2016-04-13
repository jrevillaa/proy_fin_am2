package cathering.isil.com.restaurantcathering.datos;

import android.provider.BaseColumns;

/**
 * Created by helbert on 16/05/15.
 */
public class DBItem implements BaseColumns {

    public static final String TABLE_PEDIDO = "Pedido";
    public static final String PEDIDO_ID = "id";
    public static final String PEDIDO_NOMBRE = "nombre";
    public static final String PEDIDO_TIPO = "tipo";
    public static final String PEDIDO_CANTIDAD = "cantidad";
    public static final String PEDIDO_MONTO = "monto";
    public static final String PEDIDO_STATUS = "status";

    public static final String TABLE_LOCAL = "Local";
    public static final String LOCAL_ID = "id_codigo";
    public static final String LOCAL_NOMBRE = "locnombre";
    public static final String LOCAL_UBICACION = "locubicacion";
    public static final String LOCAL_HORARIO = "lochorario";
    public static final String LOCAL_EMAIL = "locemail";
    public static final String LOCAL_TELEFONO = "loctelefono";

    public static final String TABLE_PLATO = "Platos";
    public static final String PLATO_ID = "id";
    public static final String PLATO_NOMBRE = "nombre";
    public static final String PLATO_DESCRIPCION = "descripcion";
    public static final String PLATO_PRECIO = "precio";
    public static final String PLATO_IMAGEN = "imagen";
    public static final String PLATO_TIPO = "tipo";

    public static final String TABLE_TRAGO = "Tragos";
    public static final String TRAGO_ID = "id_trago";
    public static final String TRAGO_NOMBRE = "tranombre";
    public static final String TRAGO_DESCRIPCION = "tradescripcion";
    public static final String TRAGO_PRECIO = "traprecio";
    public static final String TRAGO_IMAGEN = "traimagen";
    public static final String TRAGO_IMAGEN_PATH_LOCAL = "traimagenpath";


}
