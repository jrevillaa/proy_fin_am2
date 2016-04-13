package cathering.isil.com.restaurantcathering.helpers;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import cathering.isil.com.restaurantcathering.datos.DBItem;

/**
 * Created by helbert on 26/06/15.
 */
public class LoadData  {

    private Context contexto;
    private JSONObject jObj;


    private static final String TAG_DATA = "CARTA";
    private Model model;
    private DBItem items;


    public LoadData(Context contexto){
        this.contexto=contexto;
        model = new Model(contexto);
    }


    public boolean loadPlatos(){

        try {
            String json = loadJSONFromAsset();

            if(!json.isEmpty()){

                Log.i("response services platos", json);

                jObj = new JSONObject(json);

                JSONArray datos = jObj.getJSONArray(TAG_DATA);

                if(registerPlatos(datos))
                    return true;


            }

            return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("erro services", e.getMessage());
            return false;
        }

    }

    private boolean registerPlatos(JSONArray itemPlatos){

        try{


            for(byte x=0;x<itemPlatos.length();x++){

                JSONObject node_platos = itemPlatos.getJSONObject(x);

                model.registerPlatos(Integer.parseInt(node_platos.getString(items.PLATO_ID)),
                        node_platos.getString(items.PLATO_NOMBRE),
                        node_platos.getString(items.PLATO_DESCRIPCION),
                        node_platos.getString(items.PLATO_PRECIO),
                        node_platos.getString(items.PLATO_IMAGEN),
                        node_platos.getString(items.PLATO_TIPO));

            }

            return true;
        }catch(Exception e){

            return false;
        }

    }


    private String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = contexto.getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }



}
