package eventos;

import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.n550j.weekendtravel.DialogoFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by N550J on 22/04/2015.
 */
public class ClickListener extends Fragment implements GoogleMap.OnMapClickListener ,GoogleMap.OnMapLongClickListener,
            GoogleMap.OnMarkerClickListener{



    @Override


    public void onMapClick(LatLng latLng) {
        Log.v("Clic  latitude: ", String.valueOf(latLng.latitude));
        Log.v("Clic  longitude: ", String.valueOf(latLng.longitude));


    }

    public  void onMapLongClick(LatLng latLng){
        Log.v("Cliqueaste  latitude: ", String.valueOf(latLng.latitude));
        Log.v("Cliqueaste  longitude: ", String.valueOf(latLng.longitude));

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getActivity(), "funciona", Toast.LENGTH_LONG).show();

        return false;
    }
}
