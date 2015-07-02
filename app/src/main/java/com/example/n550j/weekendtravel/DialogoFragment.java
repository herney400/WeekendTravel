package com.example.n550j.weekendtravel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by N550J on 28/04/2015.
 */
public class DialogoFragment extends DialogFragment implements DialogInterface.OnClickListener{
     RadioButton radioButton;

   // boton1=(Button)inflatedView.findViewById(R.id.geolocalizar);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

      //  radioButton =(RadioButton) getActivity().findViewById(R.id.restaurante);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.diaologoiconos, null))
        .setTitle(R.string.marcadores)
        .setIcon(R.drawable.restaurante);

        /*radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(getActivity(),"funciona",Toast.LENGTH_LONG).show();

            }
        });
       */
        return  builder.create();
    }

    public  void onRadioButtonClicked(View view){
         boolean checked = ((RadioButton)view).isChecked();

        switch(view.getId()) {
            case R.id.restaurante:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.balneareo:
                if (checked)
                    // Ninjas rule
                    break;
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
