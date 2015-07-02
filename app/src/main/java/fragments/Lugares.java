package fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.n550j.weekendtravel.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Geolocalizacion.Result;
import adaptadores.Adaptador;



/**
 * A simple {@link Fragment} subclass.
 */
public  class Lugares extends Fragment {
   /*Instancias globales*/


   // private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    ArrayList<Result> resultado=new ArrayList();
    public Lugares() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_lugares, container, false);
        return inflater.inflate(R.layout.fragment_lugares, container, false);


    }




    public void inicializarCardViews(View inflatedView){
      //Obtener el recycler
      //  recycler= (RecyclerView)getActivity().findViewById(R.id.reciclador);
      //  recycler.setHasFixedSize(true);
     //   Adaptador adapter=new   Adaptador(resultado);
       // recycler.setAdapter(adapter);
     //obtener el recycler
        layoutManager= new LinearLayoutManager(getActivity());

        //Crear un nuevo adaptador


      /*  recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

*/

    }
    public static Lugares newInstance(String param, String param2){

        Lugares myFragment=new Lugares();
        Bundle args=new Bundle();
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
       // UbicarLugareObj();

      ArrayList<Result> arrayPrueba= new ArrayList();

        arrayPrueba.add(new Result(R.drawable.balneareo,"id","laguna","restaurante", 2,"referido","app", "yes"));
        arrayPrueba.add(new Result(R.drawable.balneareo,"id","laguna","restaurante", 2,"referido","app", "yes"));
        arrayPrueba.add(new Result(R.drawable.balneareo,"id","laguna","restaurante", 2,"referido","app", "yes"));
        arrayPrueba.add(new Result(R.drawable.balneareo,"id","laguna","restaurante", 2,"referido","app", "yes"));
        arrayPrueba.add(new Result(R.drawable.balneareo,"id","laguna","restaurante", 2,"referido","app", "yes"));


        RecyclerView  recycler= (RecyclerView)getActivity().findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
       // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager( layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        adapter=new   Adaptador(arrayPrueba, R.layout.cardview );
        recycler.setAdapter(adapter);




    }

    public void UbicarLugareObj(){
        String URL1="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=3.375337,%20-76.541666&radius=300&types=food&key=AIzaSyDlaW9Dr7HzmLmDq1m1XiVo3vt2OTJF5gM";
        String url="http://api.androidhive.info/volley/person_object.json";
        RequestQueue quee= Volley.newRequestQueue(getActivity());

        final ProgressDialog progressDialog= ProgressDialog.show(getActivity(),"Espere por favor","estamos cargando");
        JsonObjectRequest jsonObjectReq=new JsonObjectRequest(URL1,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onConnectionFailed(response.toString());
                Log.v("Mi respuesta********************************buena", response.toString());
             //   tratarResponse(response);
                progressDialog.cancel();             }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
                progressDialog.cancel();
            }
        });
        quee.add(jsonObjectReq);
    }


  /*  public  void tratarResponse(JSONObject response){
        for (int i = 0; i <response.length() ; i++) {
            Result result=new Result();
            try {
                result.setGeometry(response.getJSONObject("geometry"));
            result.setIcon(response.getString("icon"));
                result.setId("id");
                result.setName("name");
                result.setOpening_hours(response.getJSONObject("opening_hours"));
                result.setPlace_id(response.getString("place_id"));
                result.setPrice_level(response.getInt("price_level"));
                result.setReference(response.getString("reference"));
                result.setScope(response.getString("scope"));
                result.setTypes(response.getJSONArray("types"));
                result.setVecinity(response.getString("vicinity"));
                resultado.add(i,result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/

    public void onConnectionFailed(String error) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        //Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
