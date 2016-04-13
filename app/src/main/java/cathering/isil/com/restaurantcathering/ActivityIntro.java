package cathering.isil.com.restaurantcathering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cathering.isil.com.restaurantcathering.helpers.LoadData;


/**
 * Created by helbert on 05/06/15.
 */
public class ActivityIntro extends Activity {

    //private LoadData load;
    private LoadData load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        load = new LoadData(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        load.loadPlatos();

        continueApp();

    }


    private void continueApp(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(ActivityIntro.this, ActivityContentMain.class);
                startActivity(i);
            }
        }, 3000);

    }

}
