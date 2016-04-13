package cathering.isil.com.restaurantcathering.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cathering.isil.com.restaurantcathering.R;

/**
 * Created by helbert on 27/06/15.
 */
public class FragmentUbicanos extends Fragment {

    private GoogleMap map;
    private static View view;
    private BitmapDescriptor icon;
    static final LatLng ubicacion = new LatLng(-12.131053, -77.035879);
    private CameraUpdate camera_update;
    private CameraPosition parametros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }

      try {
            view = inflater.inflate(R.layout.fragment_ubicanos, container, false);
            map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker);
       } catch (InflateException e) {

        }

        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        addPosition();
    }

    private void addPosition(){
        map.addMarker(new MarkerOptions().title(getString(R.string.app_name)).draggable(true).position(ubicacion).icon(icon)).showInfoWindow();
        parametros= new CameraPosition.Builder().target(ubicacion).zoom(15).bearing(0).tilt(0).build();
        camera_update = CameraUpdateFactory.newCameraPosition(parametros);
        map.animateCamera(camera_update);
    }
}
