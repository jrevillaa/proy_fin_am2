package cathering.isil.com.restaurantcathering.helpers;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by helbert on 17/06/15.
 */
public class DialogsProgress {

    private ProgressDialog progressDialog;
    private Context context;

    public void DialogsProgress(Context context){
        this.context=context;
    }

    public void showLoad(String message){
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideLoad(){
        progressDialog.dismiss();
    }

}
