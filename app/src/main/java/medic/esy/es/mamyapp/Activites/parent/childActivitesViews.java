package medic.esy.es.mamyapp.Activites.parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.R;

public class childActivitesViews extends Fragment {


    private TextView babynameintroduction,bodydata;

  private TextView mychildActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View root = inflater.inflate(R.layout.fragment_child_activites_views, container, false);
        babynameintroduction=(TextView)root.findViewById(R.id.babyname);
        bodydata=(TextView)root.findViewById(R.id.bodyData);

        String babyname =commonParent.currentuser.getName();

        babynameintroduction.setText("Your Child "+babyname +" "+"Activities For Day this Day");


        String Activity =commonParent.currentuser.getActivity();
        bodydata.setText(Activity);
        Toast.makeText(getActivity(),Activity,Toast.LENGTH_SHORT).show();

//        mychildActivity.setText(Activity);





        return root;
    }


}
