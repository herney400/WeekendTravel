package fragments;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.n550j.weekendtravel.DialogoFragment;
import com.example.n550j.weekendtravel.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import Geolocalizacion.Result;
import eventos.ClickListener;

//import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * A simple {@link Fragment} subclass.
 */

public   class Mapa extends  Fragment implements   LocationListener , GoogleMap.OnMarkerClickListener,GoogleMap.OnMapLongClickListener{


    boolean markerClicked;
    LocationManager locationManager;
    private static final long TIEMPO_MIN = 10 * 1000 ; // 10 segundos
    private static final long DISTANCIA_MIN = 5 ; // 5 metros
    private static final String[] A = { "n/d", "preciso", "impreciso" };
    private static final String[] P = { "n/d", "bajo", "medio","alto" };
    private static final String[] E = { "fuera de servicio", "temporalmente no disponible ","disponible" };




    private MapView mMapView;
    private Bundle mBundle;
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap mMap;
    private  static View view;
    final LatLng lPoint = new LatLng(21 , 57);
    Button boton1,boton2,bton_estoyaqui;
    String proveedor;

    public Mapa() {
        // Required empty public constructor
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_mapa, container, false);
        boton1=(Button)inflatedView.findViewById(R.id.geolocalizar);
        boton2=(Button)inflatedView.findViewById(R.id.marcar);
        bton_estoyaqui=(Button)inflatedView.findViewById(R.id.btn_estoyaqui);

        boton1.setOnClickListener(new View.OnClickListener(){  @Override
            public void onClick(View v) {
                obtenerIconos(v);

            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity().getApplicationContext() , v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu, popup.getMenu());
                popup.show();
            }
        });

        bton_estoyaqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localizar();
               // UbicarLugareObj();
            }
        });

       // new ClickListener();/*obtener las cordenadas de los sitios*/
        MapsInitializer.initialize(getActivity());
        mMapView = (MapView) inflatedView.findViewById(R.id.map);
        mMapView.onCreate(mBundle);
        setUpMapIfNeeded(inflatedView);
        addClickListener();
        addClickListenerLon();

         //mMap.setOnMapLongClickListener(new ClickListener());
        markerClicked=false;
        return inflatedView;
     }

    public void LugareCercanosAlocalizacion(){

        String URL="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&name=cruise&key=AIzaSyA7Xtddbc4ycuO68-lmShG4buwBGnqUZ44 ";
        String URL1="https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyDlaW9Dr7HzmLmDq1m1XiVo3vt2OTJF5gM";
        String URL2="http://maps.googleapis.com/maps/api/geocode/json?latlng=39.476245,-0.349448&sensor=true";
        RequestQueue quee= Volley.newRequestQueue(getActivity());
        final ProgressDialog progressDialog= ProgressDialog.show(getActivity(),"Espere por favor","estamos cargando");

        JsonArrayRequest req=new JsonArrayRequest(URL1,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.v("Mi respuesta********************************buena",response.toString());
                progressDialog.cancel();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
                progressDialog.cancel();
                Log.e("Mi respuesta*********************************************************************es",error.toString());
            }
        });

        quee.add(req);
    }




    public void onConnectionFailed(String error) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
    public static Mapa newInstance(String param, String param2){
        Mapa myFragment=new Mapa();
        Bundle args=new Bundle();
        myFragment.setArguments(args);
        return myFragment;
    }


 public void localizar(){

     String svcName = Context.LOCATION_SERVICE;
     locationManager=(LocationManager)getActivity().getSystemService(svcName);
     muestraProveedores();
     Criteria criterio= new Criteria();
     criterio.setCostAllowed(false);
     criterio.setAltitudeRequired(false);
     criterio.setAccuracy(Criteria.ACCURACY_FINE);
     proveedor=locationManager.getBestProvider(criterio, true);
     Log.v("Mejor proveedor: " + proveedor + "\n","d");
     Log.v("Comenzamosconlaúltimlocalición conocida:", "s");

     //String proveedor= LocationManager.GPS_PROVIDER;
     Location l=locationManager.getLastKnownLocation(proveedor);
     UpdateWithNewLocation(l);



 }
   private void UpdateWithNewLocation(Location location){
       if(location!=null){
           double lat=location.getLatitude();
           double lng=location.getLongitude();

           LatLng sydney = new LatLng(lat, lng);
           mMap.setMyLocationEnabled(true);
           mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));
           mMap.addMarker(new MarkerOptions()
                   .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono))
                           // .title("Cali")
                           // .snippet("arrancamos")
                   .flat(true)
                           //.anchor(0.0f,1.0f)
                   .rotation(245)
                   .position(sydney)
                   .draggable(true));
           //  mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
           mMap.getUiSettings().setRotateGesturesEnabled(true);
           CameraPosition cameraPosition =CameraPosition.builder()
                   .target(sydney)
                   .zoom(13)
                   .bearing(90)
                   .build();
           mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),5000, null);
       }

   }
public void obtenerIconos(View view){
/*
    FragmentManager fragmentManager=getFragmentManager();
    DialogoFragment dialogFragment= new DialogoFragment();
    dialogFragment.show(fragmentManager,"alerta");*/

}
    private void addClickListener(){
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

            }
        });


    }

    private void addClickListenerLon(){
        mMap.setOnMapLongClickListener(new ClickListener());
    }


    private void setUpMapIfNeeded(View inflatedView) {
        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
            if (mMap != null) {
                localizar(); //aqui quite el setUpma()
            }
        }
    }
    private void setUpMap() {
        LatLng sydney = new LatLng(41.889, -87.622);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono))
                        // .title("Cali")
                        // .snippet("arrancamos")
                .flat(true)
                        //.anchor(0.0f,1.0f)
                .rotation(245)
                .position(sydney)
                .draggable(true));
      //  mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
     mMap.getUiSettings().setRotateGesturesEnabled(true);
     CameraPosition cameraPosition =CameraPosition.builder()
                .target(sydney)
                .zoom(13)
                .bearing(90)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),5000, null);



    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
   /* public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }*/

    public void onDestroyView(){
      /*-  super.onDestroyView();
        MapFragment f=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        if(mMap!=null){
            getFragmentManager().beginTransaction().remove(f).commit();
        }*/
    }


    public void onMapReady(GoogleMap map){
        LatLng sydney=new LatLng(-33.867,151.206);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,13));

        map.addMarker(new MarkerOptions().title("sydney").snippet("fasdfasdfasdf").position(sydney)
        );

      //  map.addMarker(new MarkerOptions().position(new LatLng(3.410834, -76.535828)).title("Market"));
    }



    /*Metodos para interfaz locationListener*/
    @Override
    public void onLocationChanged(Location location) {
        Log.v("Nueva localización: ","");
        muestraLocaliz(location);
    }

    @Override
    public void onStatusChanged(String provider, int estado, Bundle extras) {

            Log.v("Cambia estado proveedor: " + proveedor + ", estado=" + E[Math.max(0, estado)] + ", extras=" + extras + "\n", "");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.v("Proveedor habilitado: " + proveedor + "\n","");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.v("Proveedor deshabilitado: " + proveedor + "\n","");
    }

    /*Metodos para mostrar informacion*/

    /*private void log(String cadena) {
        salida.append(cadena + "\n");
    }*/

    private void muestraLocaliz(Location localizacion) {
        if (localizacion == null)
            Log.v("Localiz desconocida\n"," ");
        else
            Log.v(localizacion.toString() + "\n", "");
    }

    private void muestraProveedores() {
        Log.v("Proveelocalización: \n", " ");
        List<String> proveedores = locationManager.getAllProviders();
        for (String proveedor : proveedores) {
            muestraProveedor(proveedor);
        }
    }

    private void muestraProveedor(String proveedor) {
        LocationProvider info = locationManager.getProvider(proveedor);
        Log.v("LocationProvider[ "+"getName=" + info.getName()
                + ", isProviderEnabled="
                + locationManager.isProviderEnabled(proveedor)+", getAccuracy="
                + A[Math.max(0, info.getAccuracy())]+ ", getPowerRequirement="
                + P[Math.max(0, info.getPowerRequirement())]
                +", hasMonetaryCost=" + info.hasMonetaryCost()
                + ", requiresCell=" + info.requiresCell()
                + ", requiresNetwork=" + info.requiresNetwork()
                + ", requiresSatellite=" + info.requiresSatellite()
                + ", supportsAltitude=" + info.supportsAltitude()
                + ", supportsBearing=" + info.supportsBearing()
                + ", supportsSpeed=" + info.supportsSpeed()+" ]\n","");
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(getActivity(),"funcionaaaaaaaaa",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(getActivity(), "funciona", Toast.LENGTH_LONG).show();

    }
}
