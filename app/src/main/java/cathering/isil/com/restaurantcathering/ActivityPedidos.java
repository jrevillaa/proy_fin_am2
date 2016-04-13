package cathering.isil.com.restaurantcathering;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cathering.isil.com.restaurantcathering.adapters.AdapterPedidos;
import cathering.isil.com.restaurantcathering.helpers.Model;
import cathering.isil.com.restaurantcathering.listeners.SwipeDismissListViewTouchListener;

/**
 * Created by helbert on 21/06/15.
 */
public class ActivityPedidos extends Activity {

    TextView tp;
    TextView tt;


    private AdapterPedidos adapter;
    private ListView rv;
    private Model model;
    private ProgressDialog progressDialog;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tp = (TextView) findViewById(R.id.detail_titulos_plates);
        tt = (TextView) findViewById(R.id.detail_titulos_trago);
        rv = (ListView) findViewById(R.id.rv);
        enviar = (Button) findViewById(R.id.enviar);

       getActionBar().setDisplayShowHomeEnabled(true);
       getActionBar().setDisplayHomeAsUpEnabled(true);

        model = new Model(this);

        loadPedidos();


        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        rv,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {

                                for (int position : reverseSortedPositions) {
                                    adapter.remove(position);
                                }

                                adapter.notifyDataSetChanged();
                                loadPedidos();
                            }
                        });

        rv.setOnTouchListener(touchListener);
        rv.setOnScrollListener(touchListener.makeScrollListener());


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityPedidos.this, getString(R.string.msg_pedido),Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this,ActivityContentMain.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void loadPedidos(){
        model.listarPedido();
        adapter = new AdapterPedidos(this, model.pedidos);
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }


}
