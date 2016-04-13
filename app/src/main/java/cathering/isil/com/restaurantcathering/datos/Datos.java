package cathering.isil.com.restaurantcathering.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import cathering.isil.com.restaurantcathering.R;
import cathering.isil.com.restaurantcathering.objects.Local;
import cathering.isil.com.restaurantcathering.objects.Pedido;
import cathering.isil.com.restaurantcathering.objects.Plato;

/**
 * Created by helbert on 16/05/15.
 */
public class Datos extends SQLiteOpenHelper {

    private static final String CREATE_LOCAL = "CREATE TABLE " + DBItem.TABLE_LOCAL + " (" + DBItem.LOCAL_ID + " INTEGER," + DBItem.LOCAL_NOMBRE + " TEXT, " + DBItem.LOCAL_UBICACION + " TEXT , " + DBItem.LOCAL_HORARIO + " TEXT , "+ DBItem.LOCAL_EMAIL + " TEXT, " + DBItem.LOCAL_TELEFONO + " TEXT);";
    private static final String CREATE_PLATOS = "CREATE TABLE " + DBItem.TABLE_PLATO + " (" + DBItem.PLATO_ID + " INTEGER," + DBItem.PLATO_NOMBRE + " TEXT, " + DBItem.PLATO_DESCRIPCION + " TEXT , " + DBItem.PLATO_PRECIO + " TEXT , "+ DBItem.PLATO_IMAGEN + " TEXT, " + DBItem.PLATO_TIPO + " TEXT);";
    private static final String CREATE_TRAGOS = "CREATE TABLE " + DBItem.TABLE_TRAGO + " (" + DBItem.TRAGO_ID + " INTEGER," + DBItem.TRAGO_NOMBRE + " TEXT, " + DBItem.TRAGO_DESCRIPCION + " TEXT , " + DBItem.TRAGO_PRECIO + " TEXT , "+ DBItem.TRAGO_IMAGEN + " TEXT, " + DBItem.TRAGO_IMAGEN_PATH_LOCAL + " TEXT);";
    private static final String CREATE_PEDIDO = "CREATE TABLE " + DBItem.TABLE_PEDIDO + " (" + DBItem.PEDIDO_ID + " INTEGER," + DBItem.PEDIDO_NOMBRE + " TEXT, "  + DBItem.PEDIDO_TIPO + " TEXT, " + DBItem.PEDIDO_CANTIDAD + " INTEGER , " + DBItem.PEDIDO_MONTO + " TEXT , " + DBItem.PEDIDO_STATUS + " INTEGER);";

    private SQLiteDatabase db;
    private Context context;

    public Datos(Context context) {
        super(context, context.getString(R.string.database), null, context.getResources().getInteger(R.integer.version_database));
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(CREATE_LOCAL);
            db.execSQL(CREATE_PLATOS);
            db.execSQL(CREATE_TRAGOS);
            db.execSQL(CREATE_PEDIDO);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /******* PROFILE LOCAL ******/

    public void registerProfileLocal(int id,String nombre,String ubicacion,String horario,String email,String telefono) throws SQLException {

        try {
            db = getReadableDatabase();

            if(checkRegister(DBItem.TABLE_LOCAL, DBItem.LOCAL_ID, String.valueOf(id))==0){

                db.execSQL("delete from " + DBItem.TABLE_LOCAL);

                ContentValues values = new ContentValues();
                values.put(DBItem.LOCAL_ID, id);
                values.put(DBItem.LOCAL_NOMBRE, nombre);
                values.put(DBItem.LOCAL_UBICACION, ubicacion);
                values.put(DBItem.LOCAL_HORARIO, horario);
                values.put(DBItem.LOCAL_EMAIL, email);
                values.put(DBItem.LOCAL_TELEFONO, telefono);
                db.insert(DBItem.TABLE_LOCAL, null, values);

            }

            db.close();
        } catch (SQLException e) {

        }
    }


    public Local listarDataLocal() throws SQLException {

        Local local = null;

        db = getReadableDatabase();

        Cursor c = db.rawQuery("select * from " + DBItem.TABLE_LOCAL , null);

        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                local = new Local(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return local;

    }


    /******* PLATOS ******/

    public void registerPlatosLocal(int id,String nombre,String descripcion,String precio,String imagen,String tipo) throws SQLException {

        try {
            db = getReadableDatabase();

            if(checkRegister(DBItem.TABLE_PLATO, DBItem.PLATO_ID, String.valueOf(id))==0){

                ContentValues values = new ContentValues();
                values.put(DBItem.PLATO_ID, id);
                values.put(DBItem.PLATO_NOMBRE, nombre);
                values.put(DBItem.PLATO_DESCRIPCION, descripcion);
                values.put(DBItem.PLATO_PRECIO, precio);
                values.put(DBItem.PLATO_IMAGEN, imagen);
                values.put(DBItem.PLATO_TIPO, tipo);
                db.insert(DBItem.TABLE_PLATO, null, values);

            }

            db.close();
        } catch (SQLException e) {

        }
    }


    public ArrayList<Plato> listarCartaPlatos(String tipo) throws SQLException {

        ArrayList<Plato> values = new ArrayList<Plato>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("select * from " + DBItem.TABLE_PLATO + " WHERE " + DBItem.PLATO_TIPO + "='" + tipo + "'", null);

        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                Plato pa = new Plato(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
                values.add(pa);
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return values;

    }




    /******* PEDIDOS ******/

    public void registerPedidos(int id,String nombre,String tipo,int cantidad,String monto,int status) throws SQLException {

        try {

            db = getReadableDatabase();

            Cursor c = db.rawQuery("select * from " + DBItem.TABLE_PEDIDO + "  where " + DBItem.PEDIDO_ID + "=" + id +
                    " and " +  DBItem.PEDIDO_TIPO + "='" + tipo + "'" , null);

            if(c.getCount()==0){
                ContentValues values = new ContentValues();
                values.put(DBItem.PEDIDO_ID, id);
                values.put(DBItem.PEDIDO_NOMBRE, nombre);
                values.put(DBItem.PEDIDO_TIPO, tipo);
                values.put(DBItem.PEDIDO_CANTIDAD, cantidad);
                values.put(DBItem.PEDIDO_MONTO, monto);
                values.put(DBItem.PEDIDO_STATUS, status);
                db.insert(DBItem.TABLE_PEDIDO, null, values);
            }else{

                cantidad = getCantServiciosByPedidos(id,tipo) + cantidad;

                db = getReadableDatabase();

                String[] args = {String.valueOf(id)};
                ContentValues values = new ContentValues();
                values.put(DBItem.PEDIDO_CANTIDAD, cantidad);
                values.put(DBItem.PEDIDO_MONTO, monto);
                db.update(DBItem.TABLE_PEDIDO, values, DBItem.PEDIDO_ID + "=?", args);

            }

            db.close();

        } catch (SQLException e) {
            Log.e("error db",e.getMessage());
        }
    }


    public ArrayList<Pedido> listarPedidos() throws SQLException {

        ArrayList<Pedido> values = new ArrayList<Pedido>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("select * from " + DBItem.TABLE_PEDIDO + " WHERE " + DBItem.PEDIDO_STATUS + "=0", null);

        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                Pedido pe = new Pedido(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4), c.getInt(5));
                values.add(pe);
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return values;

    }


    public int getCantServiciosByPedidos(int id ,String tipo) throws SQLException {

        db = getReadableDatabase();
        int total=0;

        Cursor c = db.rawQuery("select " + DBItem.PEDIDO_CANTIDAD + " from " + DBItem.TABLE_PEDIDO + "  WHERE "  + DBItem.PEDIDO_ID + "=" + id + " and " + DBItem.PEDIDO_TIPO + "='" + tipo + "' and "  + DBItem.PEDIDO_STATUS + "=0", null);

        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                total = c.getInt(0);
            } while (c.moveToNext());
        }

        db.close();
        c.close();

        return total;
    }


    public int totalPedidos() throws SQLException {

        db = getReadableDatabase();
        int total=0;

        Cursor c = db.rawQuery("select count(*) from " + DBItem.TABLE_PEDIDO + "  WHERE "  + DBItem.PEDIDO_STATUS + "=0", null);

        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                total = c.getInt(0);
            } while (c.moveToNext());
        }

        db.close();
        c.close();

        return total;
    }

    public void cleanPedidos() throws SQLException {

        db = getReadableDatabase();
        db.rawQuery("delete from " + DBItem.TABLE_PEDIDO, null);
        db.close();

    }

    public boolean removePedido(int id) throws SQLException {

        try {

            db = getReadableDatabase();

            String[] codigo = {String.valueOf(id)};
            db.delete(DBItem.TABLE_PEDIDO, DBItem.PEDIDO_ID + "=?", codigo);
            db.close();

            return true;

        } catch (SQLException e) {
            return false;
        }

    }



    public int checkRegister(String table,String field,String data) {
        int total=0;
        String[] param = {data};
        Cursor c = db.rawQuery("select * from " + table + "  where " + field + "=?", param);
        total = c.getCount();
        c.close();
        return total;
    }


}
