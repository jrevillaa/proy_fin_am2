package cathering.isil.com.restaurantcathering.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import cathering.isil.com.restaurantcathering.ActivityPedidos;
import cathering.isil.com.restaurantcathering.R;
import cathering.isil.com.restaurantcathering.adapters.AdapterPlatos;
import cathering.isil.com.restaurantcathering.helpers.Model;


/**
 * Created by helbert on 16/05/15.
 */
public class FragmentCartaPlatos extends Fragment {

    private Activity activity;
    private Context context;
    private AdapterPlatos adapter;
    private Model model;
    private StaggeredGridView gridView;
    private String tipo;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        context = getActivity().getApplicationContext();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_platos, container, false);

        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        gridView = (StaggeredGridView) rootView.findViewById(R.id.grid_items);

        model = new Model(context);

        tipo = getArguments().getString(context.getString(R.string.tipo));

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

        loadCarta(tipo);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pedido, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.add:
                if(model.totalPedido()>0)
                    startActivity(new Intent(activity, ActivityPedidos.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                else
                    Toast.makeText(context, context.getText(R.string.msg_orden), Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }

    private void loadCarta(String categoria){
        model.loadPlatos(categoria);
        adapter = new AdapterPlatos(context,model.platos);
        SwingBottomInAnimationAdapter animation = new SwingBottomInAnimationAdapter(adapter);
        animation.setAbsListView(gridView);
        gridView.setAdapter(animation);
    }
}
