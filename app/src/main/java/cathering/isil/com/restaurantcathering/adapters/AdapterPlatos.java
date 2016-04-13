package cathering.isil.com.restaurantcathering.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import cathering.isil.com.restaurantcathering.R;
import cathering.isil.com.restaurantcathering.helpers.Model;
import cathering.isil.com.restaurantcathering.objects.Plato;

/**
 * Created by helbert on 01/06/15.
 */
public class AdapterPlatos extends BaseAdapter {

    private Context context;
    private ArrayList<Plato> platos;
    private DisplayImageOptions options;
    private Model model;
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public AdapterPlatos(Context context, ArrayList<Plato> platos){
        this.context=context;
        this.platos=platos;
        model = new Model(context);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

    }

    @Override
    public int getCount() {
        return platos.size();
    }

    @Override
    public Object getItem(int position) {
        return platos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final int index=position;

        Plato p = (Plato) platos.get(position);

        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_grid, parent, false);
            holder = new ViewHolder();

            assert convertView != null;

            holder.imageView = (ImageView) convertView.findViewById(R.id.ic_item);
            holder.add = (ImageButton) convertView.findViewById(R.id.add);
            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.precio = (TextView) convertView.findViewById(R.id.precio);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ImageLoader.getInstance()
                .displayImage("assets://" + p.getImagen(), holder.imageView, options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        holder.progressBar.setProgress(0);
                        holder.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        holder.progressBar.setProgress(Math.round(100.0f * current / total));
                    }
                });

        holder.nombre.setText(p.getNombre());
        holder.precio.setText("$/." + currencyFormat.format(Double.parseDouble(p.getPrecio())));

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plato p = (Plato) platos.get(index);

                double priceInUSD = (model.cantidadPedido(p.getId(), context.getString(R.string.platos)) + 1) * Double.parseDouble(p.getPrecio());


                model.registerPedido(p.getId(),p.getNombre(),context.getString(R.string.platos),1,currencyFormat.format(priceInUSD),0);
                Toast.makeText(context, context.getText(R.string.msg_add_pedido) + " " + String.valueOf(model.cantidadPedido(p.getId(),context.getString(R.string.platos))) + " " + p.getNombre() , Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }



    static class ViewHolder {
        ImageView imageView;
        ImageButton add;
        ProgressBar progressBar;
        TextView nombre;
        TextView precio;
    }

    private Bitmap getImagen(String imagen){
        File imgFile = new  File(imagen);
        //Bitmap myBitmap = null;
        Bitmap resized = null;

        if(imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            resized = Bitmap.createScaledBitmap(myBitmap, 320, 210, true);
        }

        return resized;
    }
}
