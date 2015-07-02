package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.n550j.weekendtravel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class items extends Fragment {

    public static final String ARG_PLANET_NUMBER = "items_number";
    public items() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);
    }


    public static items newInstance(String param, String param2){
        items myFragment=new items();

        Bundle args=new Bundle();

        myFragment.setArguments(args);
        return myFragment;
    }

}
