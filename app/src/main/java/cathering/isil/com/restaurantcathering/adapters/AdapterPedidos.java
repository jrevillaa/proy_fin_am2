package cathering.isil.com.restaurantcathering.adapters;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cathering.isil.com.restaurantcathering.R;
import cathering.isil.com.restaurantcathering.helpers.Model;
import cathering.isil.com.restaurantcathering.objects.Pedido;

/**
 * Created by helbert on 21/06/15.
 */
public class AdapterPedidos  extends BaseAdapter {


    public ArrayList<Pedido> pedidos;
    private static Drawable ic_p;
    private static Drawable ic_t;
    private Context context;
    private Model model;

    public AdapterPedidos(Context context, ArrayList<Pedido> pedidos){
        this.pedidos=(pedidos);
        this.context=context;
        ic_p = context.getResources().getDrawable(R.drawable.ic_pedido_plato);
        ic_t = context.getResources().getDrawable(R.drawable.ic_pedido_trago);
        model = new Model(context);
    }

    @Override
    public int getCount() {
        return pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Pedido p = (Pedido) pedidos.get(position);

        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_pedidos, parent, false);
            holder = new ViewHolder();

            assert convertView != null;

            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.cantidad = (TextView) convertView.findViewById(R.id.cantidad);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.nombre.setText(p.getNombre());
        holder.cantidad.setText(context.getString(R.string.msg_detalle_pedido_1) + " " + String.valueOf(p.getCantidad()) + " " +context.getString(R.string.msg_detalle_pedido_2) + " "  + p.getMonto());

        if(pedidos.get(position).getTipo().equals(context.getString(R.string.platos)))
            holder.icon.setImageDrawable(ic_p);
        else
            holder.icon.setImageDrawable(ic_t);

        return convertView;
    }


    static class ViewHolder {
        TextView nombre;
        TextView cantidad;
        ImageView icon;
    }

    public void remove(int position){
        model.removePedido(pedidos.get(position).getId());
        notifyDataSetChanged();
    }

}

