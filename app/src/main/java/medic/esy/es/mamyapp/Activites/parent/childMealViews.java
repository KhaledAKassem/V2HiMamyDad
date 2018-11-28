package medic.esy.es.mamyapp.Activites.parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import medic.esy.es.mamyapp.R;


public class childMealViews extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =inflater.inflate(R.layout.fragment_child_meal_views, container, false);





        return  root;
    }

}
