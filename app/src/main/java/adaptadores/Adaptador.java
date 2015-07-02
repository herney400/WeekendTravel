package adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n550j.weekendtravel.R;

import java.util.ArrayList;
import java.util.List;

import Geolocalizacion.Result;

/**
 * Created by N550J on 19/06/2015.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.AnimeViewHolder>{
        private ArrayList<Result> results;
        private int itemLayout;

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        // Campos respectivos de un item
        protected  TextView nombre_lugar;
        protected  TextView tipo_lugar;
        protected  ImageView imageView;
        public AnimeViewHolder(View v) {
            super(v);
            cv= (CardView) v.findViewById(R.id.cardview);
            nombre_lugar= (TextView) v.findViewById(R.id.nombre);
            tipo_lugar  = (TextView) v.findViewById(R.id.tipo_lugar);
            imageView = (ImageView) v.findViewById(R.id.imagen);
            Toast.makeText(v.getContext(), "Funcista aqui :D"+R.layout.cardview, Toast.LENGTH_SHORT).show();
        }
    }

    public Adaptador(ArrayList<Result> resultList, int itemLayout) {
            this.results =resultList;
            this.itemLayout=itemLayout;

    }

    @Override
    public Adaptador.AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent,false);

        AnimeViewHolder vh=new AnimeViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {

       Result resultado= results.get(position);
        holder.imageView.setImageResource(resultado.getIcon());
        holder.tipo_lugar.setText(resultado.getName() );
        holder.nombre_lugar.setText(resultado.getName());

    }
   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
       super.onAttachedToRecyclerView(recyclerView);
   }
    @Override
    public int getItemCount() {

        return results.size();
    }
}
